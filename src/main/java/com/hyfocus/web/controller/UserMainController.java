package com.hyfocus.web.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hyfocus.web.domain.CameraVO;
import com.hyfocus.web.domain.ExtraVO;
import com.hyfocus.web.domain.LensVO;
import com.hyfocus.web.domain.RentVO;
import com.hyfocus.web.service.CameraService;
import com.hyfocus.web.service.ExtraService;
import com.hyfocus.web.service.LensService;
import com.hyfocus.web.service.RentService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UserMainController {

	private String startDate; // 오픈 시간
	private String endDate; // 마감 시간

	@Autowired
	private CameraService cameraService;

	@Autowired
	private LensService lensService;

	@Autowired
	private ExtraService extraService;

	@Autowired
	private RentService rentService;

	@GetMapping("/modifyTime")
	public String modifyTimeGET(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("admin") != null) {
			log.info("modifyTimeGET()");

			// 현재 오픈 시간과 마감 시간을 넘겨줌
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);

			return "modify/modifyTime";
		} else {
			return "error/error";
		}
	}

	@PostMapping("/modifyTimeData")
	public ResponseEntity<String> modifyTimeDataPost(@RequestParam(value = "openTime") String openTime,
			@RequestParam(value = "closeTime") String closeTime, RedirectAttributes reAttr) {
		log.info("modifyTimeDataPost()");

		startDate = openTime;
		endDate = closeTime;

		String result = "FAIL";
		if (openTime != null && closeTime != null) {
			result = "SUCCESS";
		}

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/pageNotOpen")
	public String pageNotOpenGET() {
		log.info("pageNotOpenGET()");
		return "error/pageNotOpen";
	}

	@GetMapping("/main")
	public String mainGET(HttpServletRequest request, Model model, RedirectAttributes reAttr) {
		ZoneId zoneId = ZoneId.of("Asia/Seoul"); // 한국 시간
		LocalDateTime currentDateTime = LocalDateTime.now(zoneId);
		LocalDateTime targetDateTimeOpen, targetDateTimeClose;
		HttpSession session;

		try {
			targetDateTimeOpen = LocalDateTime.parse(startDate);
			targetDateTimeClose = LocalDateTime.parse(endDate);
		} catch (DateTimeParseException e) {
			log.info("날짜 형식 오류: " + e.getMessage());
			return "error/error";
		}

		if (currentDateTime.isAfter(targetDateTimeOpen) && currentDateTime.isBefore(targetDateTimeClose)) {
			session = request.getSession(false);
			session = request.getSession();
			session.setAttribute("hyfocus", "hyfocus");
			session.setMaxInactiveInterval(600);
		} else {
			return "redirect:/pageNotOpen";
		}

		if (session != null && session.getAttribute("hyfocus") != null) {
			log.info("mainGET()");

			// 카메라 리스트 가져옴
			ArrayList<CameraVO> DSLRCamList = cameraService.selectAllDataDSLR();
			ArrayList<CameraVO> FilmMCamList = cameraService.selectAllDataFilmM();
			ArrayList<CameraVO> FilmACamList = cameraService.selectAllDataFilmA();
			ArrayList<CameraVO> MirrorLessCamList = cameraService.selectAllDataMirrorLess();

			// 렌즈 리스트 가져옴
			ArrayList<LensVO> canonLensList = lensService.selectAllDataCanon();
			ArrayList<LensVO> tamronLensList = lensService.selectAllDataTamron();
			ArrayList<LensVO> sigmaLensList = lensService.selectAllDataSigma();

			// 가방/삼각대 리스트 가져옴
			ArrayList<ExtraVO> extraList = extraService.selectAllData();

			// 리스트 넘겨줌
			model.addAttribute("DSLRCamList", DSLRCamList);
			model.addAttribute("FilmMCamList", FilmMCamList);
			model.addAttribute("FilmACamList", FilmACamList);
			model.addAttribute("MirrorLessCamList", MirrorLessCamList);

			model.addAttribute("canonLensList", canonLensList);
			model.addAttribute("tamronLensList", tamronLensList);
			model.addAttribute("sigmaLensList", sigmaLensList);

			model.addAttribute("extraList", extraList);

			return "main/form";
		} else {
			return "error/error";
		}
	}

	@PostMapping("/rent")
	public synchronized ResponseEntity<Map<String, Object>> rentPOST(@RequestParam(required = false) String camera,
			@RequestParam(required = false) String lens, @RequestParam String stuInfo, Model model,
			RedirectAttributes reAttr) {

		log.info("rentPOST()");

		// 재고 확인을 위한 Map 초기화
		Map<String, Integer> inventoryCheck = new HashMap<>();
		if (camera != null && camera.length() != 0) {
			inventoryCheck.put(camera, cameraService.chkCntByName(camera));
		}
		if (lens != null && lens.length() != 0) {
			inventoryCheck.put(lens, lensService.chkCntByName(lens));
		}

		// 응답 데이터를 담을 Map
		Map<String, Object> response = new HashMap<>();

		// 재고 검사
		for (Map.Entry<String, Integer> entry : inventoryCheck.entrySet()) {
			String itemName = entry.getKey();
			int itemCount = entry.getValue();
			if (itemCount <= 0) {
				response.put("success", false);
				response.put("message", itemName + " 수량이 부족합니다.");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		}

		// 재고 충분 시 데이터 삽입 및 성공 메시지 반환
		Date createdDate = new Date();
		log.info(stuInfo + " " + rentService.insert(camera, lens, stuInfo, createdDate) + "행 INSERT 수행완료.");

		response.put("success", true);
		response.put("stuInfo", stuInfo);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/rentSuccess")
	public String rentSuccessGet(Model model, String stuInfo) {
		ArrayList<RentVO> resultList = rentService.getAllDataByStuInfo(stuInfo);
		RentVO rentVO = resultList.get(0);

		// 가방/삼각대 리스트 가져옴
		ArrayList<ExtraVO> extraList = extraService.selectAllData();

		model.addAttribute("extraList", extraList);
		model.addAttribute("rentVO", rentVO);
		return "main/success";
	}

	@PostMapping("/rentBag")
	public ResponseEntity<String> rentBagPOST(@RequestParam(value = "stuInfo") String stuInfo,
			@RequestParam(value = "bag", required = false) String bag, Model model, RedirectAttributes reAttr) {

		log.info("rentBagPOST()");
		String result = "fail";
		
		if(bag == null) {
			result = "success";
		} else {
			if(rentService.updateBad(bag, stuInfo) > 0) {
				result = "success";
			}
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/rentListForStuInfo")
	public ResponseEntity<ArrayList<RentVO>> rentListForStuInfoPOST(@RequestParam("data") String data) {
		if (data.length() < 10) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		ArrayList<RentVO> resultList = rentService.getAllDataByStuInfo(data);

		for (RentVO rentVO : resultList) {
			rentVO.setFormattedCreatedDate(rentVO.getCreatedDate());
			if (rentVO.getLensName() == null) {
				rentVO.setLensName("");
			} else if (rentVO.getCamName() == null) {
				rentVO.setCamName("");
			}
		}
		return new ResponseEntity<ArrayList<RentVO>>(resultList, HttpStatus.OK);
	}
}

package com.hyfocus.web.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

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
	
	//		*********************
	//		**** 보여줄 시간 설정 ****
	//		*********************

	private String setDate = "2024-11-06T14:30:00"; 
		
	
	
	
	@Autowired
	private CameraService cameraService;

	@Autowired
	private LensService lensService;

	@Autowired
	private ExtraService extraService;

	@Autowired
	private RentService rentService;

	private String isNullChkList(ArrayList<String> List) {
		String result = null;
		for (String str : List) {
			if (str != null && !str.isEmpty()) {
				int index = str.indexOf('(');
				if (index != -1) {
					result = str.substring(0, index).trim();
				} else {
					result = str;
				}
				break;
			}
		}
		return result;
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
        LocalDateTime targetDateTime;
        HttpSession session;

        try {
            targetDateTime = LocalDateTime.parse(setDate); 
        } catch (DateTimeParseException e) {
        	log.info("날짜 형식 오류: " + e.getMessage());
            return "error/error";
        }

        if (currentDateTime.isAfter(targetDateTime)) {
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
	public String rentPOST(@RequestParam(required = false) String dslr,
			@RequestParam(required = false) String mirrorless, @RequestParam(required = false) String filmAuto,
			@RequestParam(required = false) String filmManual, @RequestParam(required = false) String canonLens,
			@RequestParam(required = false) String tamronLens, @RequestParam(required = false) String sigmaLens,
			@RequestParam(required = false) String bag, @RequestParam(required = false) String tripod,
			@RequestParam String stuInfo, Model model, RedirectAttributes reAttr) {

		log.info("rentPOST()");

		ArrayList<String> camList = new ArrayList<>();
		camList.add(dslr);
		camList.add(mirrorless);
		camList.add(filmAuto);
		camList.add(filmManual);

		String camera = isNullChkList(camList);

		ArrayList<String> lensList = new ArrayList<>();
		lensList.add(canonLens);
		lensList.add(tamronLens);
		lensList.add(sigmaLens);

		String lens = isNullChkList(lensList);

		log.info(stuInfo);
		Date createdDate = new Date();
		log.info(rentService.insert(camera, lens, bag, tripod, stuInfo, createdDate) + "행 INSERT 수행완료.");
		RentVO rentVO = new RentVO(0, camera, lens, bag, tripod, stuInfo, createdDate, "", "");
		
		model.addAttribute("rentVO", rentVO);
		
		return "main/success";
	}

	@PostMapping("/chkCnt")
	public ResponseEntity<Integer> chkCntPost(@RequestParam("data") String data) {
		log.info("chkCntPost()");

		int result = 0;
		if (data.equals("카메라 가방")) {
			result = extraService.chtCntByBag(data);
		} else if (data.equals("삼각대")) {
			result = extraService.chkCntByTripod(data);
		} else if (data.charAt(0) >= '0' && data.charAt(0) <= '9') {
			result = lensService.chkCntByName(data);
		} else {
			result = cameraService.chkCntByName(data);
		}

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}

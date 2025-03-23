package com.hyfocus.web.controller;

import java.util.ArrayList;
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

import com.hyfocus.web.domain.CameraVO;
import com.hyfocus.web.domain.ExtraVO;
import com.hyfocus.web.domain.LensVO;
import com.hyfocus.web.service.CameraService;
import com.hyfocus.web.service.ExtraService;
import com.hyfocus.web.service.LensService;
import com.hyfocus.web.service.RentService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class AdminModifyController {

	@Autowired
	private CameraService cameraService;

	@Autowired
	private LensService lensService;

	@Autowired
	private ExtraService extraService;

	@Autowired
	private RentService rentService;

	@GetMapping("/modifyList")
	public String modifyListGET(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("admin") != null) {
			log.info("modifyListGET()");

			// 카메라 리스트 가져옴
			ArrayList<CameraVO> camList = cameraService.selectAllData();

			// 렌즈 리스트 가져옴
			ArrayList<LensVO> lensList = lensService.selectAllData();

			// 가방/삼각대 리스트 가져옴
			ArrayList<ExtraVO> extraList = extraService.selectAllData();

			// 리스트 넘겨줌
			model.addAttribute("camList", camList);
			model.addAttribute("lensList", lensList);
			model.addAttribute("extraList", extraList);

			return "modify/modifyList";
		} else {
			return "error/error";
		}
	}

	@PostMapping("/modifyListToDetail")
	public ResponseEntity<Map<String, Object>> modifyListToDetailPOST(Model model) {
		// 카메라 리스트 가져옴
		ArrayList<CameraVO> camList = cameraService.selectAllData();

		// 렌즈 리스트 가져옴
		ArrayList<LensVO> lensList = lensService.selectAllData();

		// 가방/삼각대 리스트 가져옴
		ArrayList<ExtraVO> extraList = extraService.selectAllData();

		// 응답 객체 생성
	    Map<String, Object> response = new HashMap<>();
	    response.put("camList", camList);
	    response.put("lensList", lensList);
	    response.put("extraList", extraList);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/modifyListData")
	public String modifyListDataPost(@RequestParam(value = "camSelect", required = false) String camSelect,
			@RequestParam(value = "camCnt", required = false) Integer camCnt,
			@RequestParam(value = "lensSelect", required = false) String lensSelect,
			@RequestParam(value = "lensCnt", required = false) Integer lensCnt,
			@RequestParam(value = "extraSelect", required = false) String extraSelect,
			@RequestParam(value = "extraCnt", required = false) Integer extraCnt) {
		log.info("modifyListDataPost()");

		if (camSelect != null && !camSelect.isEmpty()) {
			log.info(cameraService.modifyCameraCnt(camSelect, camCnt) + "행 카메라 갯수 수정완료");
		}

		if (lensSelect != null && !lensSelect.isEmpty()) {
			log.info(lensService.modifyLensCnt(lensSelect, lensCnt) + "행 렌즈 갯수 수정완료");
		}

		if (extraSelect != null && !extraSelect.isEmpty()) {
			log.info(extraService.modifyExtraCnt(extraSelect, extraCnt) + "행 부가물품 갯수 수정완료");
		}

		return "modify/modifyList";
	}

	@PostMapping("/modifyUserData")
	public ResponseEntity<Integer> modifyUserDataPost(@RequestParam("rentNo") Integer rentNo,
			@RequestParam(value = "stuInfo", required = false) String stuInfo,
			@RequestParam(value = "camName", required = false) String camName,
			@RequestParam(value = "lensName", required = false) String lensName,
			@RequestParam(value = "extraName", required = false) String extraName) {
		log.info("modifyUserDataPost()");
		
		log.info(rentNo + " " + stuInfo + " " + camName + " " + lensName + " " + extraName);
		
		if (stuInfo != null && stuInfo.length() > 9) {
			log.info(rentService.modifyUserData(rentNo, stuInfo) + "행 학번/이름 수정완료");
		}

		if (camName != null && !camName.isEmpty()) {
			log.info(rentService.modifyUserCamera(rentNo, camName) + "행 카메라 수정완료");
		}

		if (lensName != null && !lensName.isEmpty()) {
			if (lensName.equals("cancel")) lensName = "";
			log.info(rentService.modifyUserLens(rentNo, lensName) + "행 렌즈 수정완료");
		}

		if (extraName != null && !extraName.isEmpty()) {
			log.info(rentService.modifyUserExtra(rentNo, extraName) + "행 부가물품 수정완료");
		}

		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}

	@PostMapping("/camData")
	public ResponseEntity<CameraVO> camDataPost(@RequestParam("data") String data) {
		log.info("camDataPost()");

		CameraVO cameraVO = cameraService.selectAllDataByName(data);

		return new ResponseEntity<CameraVO>(cameraVO, HttpStatus.OK);
	}

	@PostMapping("/lensData")
	public ResponseEntity<LensVO> lensDataPost(@RequestParam("data") String data) {
		log.info("lensDataPost()");

		LensVO lensVO = lensService.selectAllDataByName(data);

		return new ResponseEntity<LensVO>(lensVO, HttpStatus.OK);
	}

	@PostMapping("/extraData")
	public ResponseEntity<ExtraVO> extraDataPost(@RequestParam("data") String data) {
		log.info("extraDataPost()");

		ExtraVO extraVO = extraService.selectAllDataByName(data);

		return new ResponseEntity<ExtraVO>(extraVO, HttpStatus.OK);
	}
	
	@PostMapping("/allDelList")
	public ResponseEntity<String> allDelListPost() {
		log.info("allDelListPost()");
		
		String result = "fail";
		
		if(rentService.deleteAllRentForList() > 0) {
			result = "success";
		}
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@PostMapping("/allChkRent")
	public ResponseEntity<String> allChkRentPost() {
		log.info("allChkRentPost()");
		
		String result = "fail";
		
		if(rentService.updateAllRentForList() > 0) {
			result = "success";
		}
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@PostMapping("/allChkReturn")
	public ResponseEntity<String> allChkReturnPost() {
		log.info("allChkReturnPost()");
		
		String result = "fail";
		
		if(rentService.updateAllReturnForList() > 0) {
			result = "success";
		}
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
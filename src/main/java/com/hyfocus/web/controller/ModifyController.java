package com.hyfocus.web.controller;

import java.util.ArrayList;

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

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ModifyController {

	@Autowired
	private CameraService cameraService;

	@Autowired
	private LensService lensService;

	@Autowired
	private ExtraService extraService;

	@GetMapping("/modifyList")
	public String modifyListGET(Model model) {
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
}
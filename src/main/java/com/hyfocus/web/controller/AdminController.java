package com.hyfocus.web.controller;

import java.util.List;

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

import com.hyfocus.web.domain.RentVO;
import com.hyfocus.web.service.RentService;
import com.hyfocus.web.util.PageMaker;
import com.hyfocus.web.util.Pagination;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class AdminController {

	@Autowired
	private RentService rentService;

	@GetMapping("/admin")
	public String adminGET() {
		return "error/adminChk";
	}

	@PostMapping("/adminChk")
	public ResponseEntity<Integer> adminChkPOST(HttpServletRequest request, @RequestParam("password") String password) {
		int result = 0;
		if (password.equals("198023")) {
			// 세션 30분 설정
			HttpSession session = request.getSession();
			session.setAttribute("admin", "admin");
			session.setMaxInactiveInterval(600);

			result = 1;
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("/rentList")
	public String rentListGET(HttpServletRequest request, Model model, Pagination pagination) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("admin") != null) {
			log.info("rentListGET()");

			// 페이징 처리 리스트
			List<RentVO> rentList = rentService.getPagingBoards(pagination);

			PageMaker pageMaker = new PageMaker();
			pageMaker.setPagination(pagination);
			pageMaker.setTotalCount(rentService.getTotalCount(pagination));

			model.addAttribute("pageMaker", pageMaker);
			model.addAttribute("rentList", rentList);

			return "list/rentList";
		} else {
			return "error/error";
		}
	}

	@GetMapping("/allRentList")
	public String allRentListGET(HttpServletRequest request, Model model, Pagination pagination) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("admin") != null) {
			log.info("allRentListGET()");

			// 페이징 처리 리스트
			List<RentVO> rentList = rentService.getPagingBoards(pagination);

			PageMaker pageMaker = new PageMaker();
			pageMaker.setPagination(pagination);
			pageMaker.setTotalCount(rentService.getTotalCount(pagination));

			model.addAttribute("pageMaker", pageMaker);
			model.addAttribute("rentList", rentList);

			return "list/allRentList";
		} else {
			return "error/error";
		}
	}

	@GetMapping("/detail")
	public String detailGET(HttpServletRequest request, Model model, int rentNo) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("admin") != null) {
			log.info("detailGET()");

			RentVO rentVO = rentService.getAllDataByRentNo(rentNo);

			model.addAttribute("rentVO", rentVO);

			return "list/detail";
		} else {
			return "error/error";
		}
	}

	// 수정
	@PostMapping("/modify")
	public ResponseEntity<Integer> modifyPOST(@RequestParam("rentNo") String rentNo) {

		int result = 1;

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	// 삭제
	@PostMapping("/delete")
	public ResponseEntity<Integer> deletePOST(@RequestParam("rentNo") String rentNo) {
		log.info("deletePOST()");

		int result = rentService.delete(Integer.parseInt(rentNo));

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	// 대여 확인
	@PostMapping("/rentChk")
	public ResponseEntity<Integer> rentChkPOST(@RequestParam("rentNo") String rentNo) {
		log.info("rentChkPOST()");

		int result = rentService.rent(Integer.parseInt(rentNo));

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	// 반납
	@PostMapping("/returnRent")
	public ResponseEntity<Integer> returnRentPOST(@RequestParam("rentNo") String rentNo) {
		log.info("returnRentPOST()");

		RentVO rentVO = rentService.getAllDataByRentNo(Integer.parseInt(rentNo));

		int result = rentService.returnRent(rentVO);

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}

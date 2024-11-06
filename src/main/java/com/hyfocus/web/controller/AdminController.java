package com.hyfocus.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

	@GetMapping("/exportExcel")
	public void exportToExcel(HttpServletRequest request, HttpServletResponse response, Pagination pagination)
			throws IOException {
		List<RentVO> rentList = rentService.getPagingBoards(pagination);

		// 엑셀 파일 생성
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("대여 목록");

		// 헤더 생성
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("신청시간");
		headerRow.createCell(1).setCellValue("이름");
		headerRow.createCell(2).setCellValue("카메라");
		headerRow.createCell(3).setCellValue("렌즈");
		headerRow.createCell(4).setCellValue("카메라가방");
		headerRow.createCell(5).setCellValue("삼각대");

		// 데이터 추가
		int rowNum = 1;
		for (RentVO rentVO : rentList) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(rentVO.getCreatedDate());
			row.createCell(1).setCellValue(rentVO.getStuInfo());
			row.createCell(2).setCellValue(rentVO.getCamName());
			row.createCell(3).setCellValue(rentVO.getLensName());
			row.createCell(4).setCellValue(rentVO.getBag());
			row.createCell(5).setCellValue(rentVO.getTripod());
		}

		// 응답 설정
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=rent_list.xlsx");

		// 엑셀 파일을 응답으로 출력
		try (OutputStream out = response.getOutputStream()) {
			workbook.write(out);
			out.flush();
		} finally {
			workbook.close();
		}

		// 응답 버퍼 비우기
		response.flushBuffer();
	}
}

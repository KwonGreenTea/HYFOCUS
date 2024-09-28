package com.hyfocus.web;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest request) {
        ZoneId zoneId = ZoneId.of("Asia/Seoul"); // 한국 시간
        LocalDateTime currentDateTime = LocalDateTime.now(zoneId);
        LocalDateTime targetDateTime;

        try {
            targetDateTime = LocalDateTime.parse("2024-09-28T20:55:00"); // 보여줄 시작 시간
        } catch (DateTimeParseException e) {
            // 파싱 오류 처리
            System.err.println("날짜 형식 오류: " + e.getMessage());
            return "redirect:/pageNotOpen";
        }

        if (currentDateTime.isAfter(targetDateTime)) {
        	HttpSession session = request.getSession();
        	session.setAttribute("user", "user");
    		session.setMaxInactiveInterval(1800);
            return "redirect:/main"; 
        } else {
            return "redirect:/pageNotOpen"; 
        }
    }
}

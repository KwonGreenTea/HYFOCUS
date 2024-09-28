package com.hyfocus.web;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(RedirectAttributes reAttr) {
		ZoneId zoneId = ZoneId.of("Asia/Seoul"); // 한국 시간
        LocalDateTime currentDateTime = LocalDateTime.now(zoneId);
        LocalDateTime targetDateTime = LocalDateTime.parse("2024-09-28T20:35:00"); // 보여줄 시작 시간
        if (currentDateTime.isAfter(targetDateTime)) {
        	return "redirect:/main";
        } else {
        	return "redirect:/pageNotOpen";
        }
	}
}

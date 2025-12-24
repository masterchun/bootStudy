package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BusanController {
	private String[] title = {
		"", "명소", "문화 시설", "축제", "숙박", "쇼핑", "음식점", "부산 동영상", "테마 여행"
	};
	
	@GetMapping("/busan")
	public String busan_list(@RequestParam(name = "type", required = false) String type, Model model) {
		if (type == null) {
			type = "1";
		}
		model.addAttribute("title", title[Integer.parseInt(type)]);
		model.addAttribute("busan_jsp", "../busan/busan_list.jsp");
		model.addAttribute("main_jsp", "../busan/busan_main.jsp");
		return "main/main";
	}
}

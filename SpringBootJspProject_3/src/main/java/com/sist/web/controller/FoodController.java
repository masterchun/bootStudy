package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FoodController {
	
	@GetMapping("/food/list")
	public String food_list() {
		return "food/list";
	}
	
	@GetMapping("/food/detail")
	public String food_detail(@RequestParam("fno") int fno, Model model) {
		model.addAttribute("fno", fno);
		return "food/detail";
	}
}

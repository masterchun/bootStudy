package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecipeController {
	@GetMapping("/recipe/list")
	public String recipe_list() {
		return "recipe/list";
	}
	
	@GetMapping("/recipe/detail")
	public String recipe_detail(@RequestParam("no") int no, Model model) {
		model.addAttribute("no", no);
		return "recipe/detail";
	}
}

package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.FoodService;
import com.sist.web.vo.FoodVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FoodController {
	private final FoodService fService;
	
	@GetMapping("/food/find")
	public String food_find() {
		
		return "food/find";
	}
	
	@GetMapping("/food/detail")
	public String food_detail(@RequestParam("fno") int fno, Model model) {
		FoodVO vo = fService.foodDetailData(fno);
		
		model.addAttribute("vo", vo);
		return "food/detail";
	}
}

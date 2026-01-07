package com.sist.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.FoodService;
import com.sist.web.vo.FoodVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FoodController {
	private final FoodService fService;

	@GetMapping("/list")
	public String food_list(@RequestParam(name = "page", required = false) String page, Model model) {
		if (page == null) {
			page = "1";
		}
		
		int curpage = Integer.parseInt(page);
		
		Map<String, Object> map = new HashMap<>();
		map.put("pStart", (curpage - 1) * 12);
		List<FoodVO> list = fService.foodListData(map);
		int totalpage = fService.foodTotalPage();
		
		model.addAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		
		return "list";
	}
	
	@GetMapping("/detail")
	public String food_detail(@RequestParam("fno") int fno, Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("pNo", fno);
		FoodVO vo = fService.foodDetailData(map);
		
		model.addAttribute("vo", vo);
		return "detail";
	}
	
	@RequestMapping("/find")
	public String food_find(@RequestParam(name = "fs", required = false) String[] fsArr,
			@RequestParam(name = "ss", required = false) String ss,
			@RequestParam(name = "page", required = false) String page, Model model) {
		
		if (page == null) {
			page = "1";
		}
		
		int curpage = Integer.parseInt(page);
		
		Map<String, Object> map = new HashMap<>();
		map.put("fsArr", fsArr);
		map.put("ss", ss);
		map.put("start", (curpage - 1) * 12);
		
		List<FoodVO> list = fService.foodFindData(map);
		int totalpage = fService.foodFindTotalData(map);
		
		model.addAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		model.addAttribute("ss", ss);
		
		return "find";
	}
}

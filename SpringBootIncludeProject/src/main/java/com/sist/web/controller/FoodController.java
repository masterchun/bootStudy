package com.sist.web.controller;

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

/*
 * 		SSR / SCR
 * 	 서버 렌더링  클라이언트 렌더링
 */

@Controller
@RequiredArgsConstructor
public class FoodController {
	private final FoodService fService;
	
	@GetMapping("/food/detail")
	public String food_detail(@RequestParam("fno") int fno, Model model) {
		FoodVO vo = fService.foodDetailData(fno);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "food/detail");
		return "main/main";
	}
	
	// Get + Post = RequestMapping ( 사용 금지 권장)
	@RequestMapping("/food/find")
	public String food_fing(@RequestParam(name = "column", required = false) 
							String column, 
							@RequestParam(name = "ss", required = false) 
							String ss, 
							@RequestParam(name =  "page", required = false)
							String page, Model model) {
		if(page == null) {
			page = "1";
		}
		if(column == null) {
			column = "all";
		}
		int curpage = Integer.parseInt(page);

		Map map = new HashMap();
		map.put("column", column);
		map.put("ss", ss);
		map.put("start", (curpage * 12) - 12);
		
		List<FoodVO> list = fService.foodFindData(map);
		int totalpage = fService.foodFindTotalPage(map);
		
		final int BLOCK = 10;
		int startPage = ((curpage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curpage - 1) / BLOCK * BLOCK) + BLOCK;
		
		if(endPage > totalpage) {
			endPage = totalpage;
		}
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("column", column);
		if(column != null) {
			model.addAttribute("ss", ss);
		}
		
		model.addAttribute("main_html", "food/find");
		return "main/main";
	}
}

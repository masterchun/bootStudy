package com.sist.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.CampingService;
import com.sist.web.vo.CampingVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CampingController {
	private final CampingService cService;
	
	@GetMapping("/camping/list")
	public String camping_list(@RequestParam(name = "page", required = false) String page, Model model) {
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		int start = (curpage - 1) * 12;
		List<CampingVO> list = cService.campingListData(start);
		int totalpage = cService.campingTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((curpage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curpage - 1) / BLOCK * BLOCK) + BLOCK;
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		model.addAttribute("main_html", "camping/list");
		return "main/main";
	}
}

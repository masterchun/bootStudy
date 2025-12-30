package com.sist.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.FoodService;
import com.sist.web.vo.FoodVO;

import lombok.RequiredArgsConstructor;

/*
 * 		요청 데이터
 * 			Vue / React (JSON) @RequestBody
 * 		@ControllerAdvice : 예외 처리 공통으로 처리
 * 		@Aspect : AOP
 * 		interceptor, fileupload
 * 		----------------------------------------
 * 		security / batch / websocket
 */

@Controller
@RequiredArgsConstructor
public class MainController {
	private final FoodService fService;
	
	@GetMapping("/")
	public String main_page(@RequestParam(name = "page", required = false) String page, Model model) {
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		int start = (curpage - 1) * 12;
		List<FoodVO> list = fService.foodListData(start);
		int totalpage = fService.foodTotalPage();
		
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
		
		model.addAttribute("main_html", "main/home");
		return "main/main";
	}
}

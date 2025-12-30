package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.RecipeService;
import com.sist.web.vo.FoodVO;
import com.sist.web.vo.RecipeDetailVO;
import com.sist.web.vo.RecipeVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RecipeRestController {
	private final RecipeService rService;
	
	@GetMapping("/recipe/list_vue/")
	public ResponseEntity<Map> recipe_list_vue(@RequestParam(name = "page", required = false) int page) {
		Map map = new HashMap(); 
		
		try {
			int start = (page - 1) * 12;
			List<RecipeVO> list = rService.recipeListData(start);
			int totalpage = rService.recipeTotalPage();
			
			final int BLOCK = 10;
			int startPage = ((page - 1) / BLOCK * BLOCK) + 1;
			int endPage = ((page - 1) / BLOCK * BLOCK) + BLOCK;
			if (endPage > totalpage) {
				endPage = totalpage;
			}
			
			map.put("list", list);
			map.put("totalpage", totalpage);
			map.put("curpage", page);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping("/recipe/detail_vue/")
	public ResponseEntity<RecipeDetailVO> recipe_detail_vue(@RequestParam("no") int no) {
		RecipeDetailVO vo = new RecipeDetailVO();
		
		try {
			vo = rService.recipeDetailData(no);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
}


























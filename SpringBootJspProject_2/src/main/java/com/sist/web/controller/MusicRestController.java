package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.MusicService;
import com.sist.web.vo.MusicVO;

@RestController
public class MusicRestController {
	
	@Autowired
	private MusicService mService;
	
	@GetMapping("/list_vue")
	public Map music_list_vue(@RequestParam(name = "page", required = false) int page) {
		int rowSize = 12;
		int start = (rowSize * page) - (rowSize - 1);
		int end = rowSize * page;
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<MusicVO> list = mService.musicListData(map);
		int totalpage = mService.musicTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((page - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((page - 1) / BLOCK * BLOCK) + BLOCK;
		
		if(endPage > totalpage) endPage = totalpage;
		
		map = new HashMap();
		map.put("list", list);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		
		return map;
	}
	
}

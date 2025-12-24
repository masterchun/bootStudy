package com.sist.web.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.BusanService;
import com.sist.web.vo.BusanVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BusanRestController {
	private final BusanService bService;
	
	@GetMapping("/busan/list_vue/")
	public ResponseEntity<Map> busan_list(
			@RequestParam("type") int type,
			@RequestParam("page") int page) {
		Map map = new HashMap();
		
		try {
			map.put("type", type);
			map.put("start", (page - 1) * 6);
			
			List<BusanVO> list = bService.busanListData(map);
			int totalpage = bService.busanTotalPage(map);
			
			final int BLOCK = 10;
			int startPage = ((page - 1) / BLOCK * BLOCK) + 1;
			int endPage = ((page - 1) / BLOCK * BLOCK) + BLOCK;
			if (endPage > totalpage) {
				endPage = totalpage;
			}
			
			map = new HashMap();
			map.put("list", list);
			map.put("totalpage", totalpage);
			map.put("curpage", page);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			map.put("type", type);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}

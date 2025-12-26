package com.sist.web.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.BoardService;
import com.sist.web.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardRestController {
	private final BoardService bService;
	
	@GetMapping("/board/list_vue/")
	public ResponseEntity<Map> board_list_vue(@RequestParam("page") int page) {
		Map map = new HashMap();
		
		try {
			List<BoardVO> list = bService.boardListData((page - 1) * 10);
			int totalpage = bService.boardTotalPage();
			
			map.put("list", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping("/board/insert_vue/")
	public ResponseEntity<Map> board_insert_vue(@RequestBody BoardVO vo) {
		Map map = new HashMap();
		
		try {
			bService.boardInsert(vo);
			
			map.put("msg", "yes");
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping("/board/detail_vue/")
	public ResponseEntity<BoardVO> board_detail_vue(@RequestParam("no") int no) {
		BoardVO vo = null;
		
		try {
			vo = bService.boardDetailData(no);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	@DeleteMapping("/board/delete_vue/")
	public ResponseEntity<Map> board_delete_vue(@RequestParam("no") int no, @RequestParam("pwd") String pwd) {
		Map map = new HashMap();
		
		try {
			String res = bService.boardDelete(no, pwd);
			
			map.put("msg", res);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping("/board/update_vue/")
	public ResponseEntity<BoardVO> board_update_vue(@RequestParam("no") int no) {
		BoardVO vo = null;
		
		try {
			vo = bService.boardDetailData(no);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	@PutMapping("/board/update_ok_vue/")
	public ResponseEntity<Map> board_update_ok_vue(@RequestBody BoardVO vo) {
		Map map = new HashMap();
		
		try {
			String res = bService.boardUpdate(vo);
			map.put("msg", res);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}





























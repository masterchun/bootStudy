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

import com.sist.web.service.ReplyService;
import com.sist.web.vo.ReplyVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReplyRestController {
	
	private final ReplyService rService;
	
	public Map<String, Object> commonListData(int cno, int type) {
		Map<String, Object> map = new HashMap<>();
		List<ReplyVO> list = rService.replyListData(cno, type);
		
		map.put("list", list);
		map.put("cno", cno);
		map.put("type", type);
		
		return map;
	}
	
	@GetMapping("reply/list_vue/")
	public ResponseEntity<Map<String, Object>> reply_list_vue(
			@RequestParam("cno") int cno, @RequestParam("type") int type) {
		Map<String, Object> map = new HashMap<>();
		
		try {
			map = commonListData(cno, type);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping("reply/insert_vue/")
	public ResponseEntity<Map<String, Object>> reply_insert(
			@RequestBody ReplyVO vo, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		
		try {
			String id = (String) session.getAttribute("id");
			String name = (String) session.getAttribute("name");
			
			vo.setId(id);
			vo.setName(name);
			
			rService.replyInsert(vo);

			map = commonListData(vo.getCno(), vo.getType());
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PutMapping("reply/update_vue/")
	public ResponseEntity<Map<String, Object>> update_insert(
			@RequestBody ReplyVO vo, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		
		try {
			rService.replyUpdate(vo);

			map = commonListData(vo.getCno(), vo.getType());
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@DeleteMapping("reply/delete_vue/")
	public ResponseEntity<Map<String, Object>> reply_delete_vue(
			@RequestParam("cno") int cno, @RequestParam("type") int type, @RequestParam("no") int no) {
		Map<String, Object> map = new HashMap<>();
		
		try {
			rService.replyDelete(no);

			map = commonListData(cno, type);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}

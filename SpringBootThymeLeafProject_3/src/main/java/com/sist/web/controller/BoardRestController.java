package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.BoardService;
import com.sist.web.vo.BoardVO;

import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
public class BoardRestController {
	private BoardService bService;
	
	@Autowired
	public BoardRestController(BoardService bService) {
		this.bService = bService;
	}
	
	@PostMapping("/board/update_ok")
	public String board_update_ok(@ModelAttribute("vo") BoardVO vo) {
		String result = "";
		boolean bCheck = bService.boardUpdate(vo);
		if(bCheck == true) {
			result = "<script>"
				   + "location.href=\"/board/detail?no=" + vo.getNo() + "\""
				   + "</script>";
		} else {
			result = "<script>"
				   + "alert(\"비밀번호가 틀립니다\");"
				   + "history.back();"
				   + "</script>";
		}
		
		return result;
	}
	
	@PostMapping("/board/delete_ok")
	public String board_delete_ok(@RequestParam("no") int no, @RequestParam("pwd") String pwd) {
		String result = "";
		boolean bCheck = bService.boardDelete(no, pwd);
		if(bCheck == true) {
			result = "<script>"
				   + "location.href=\"/board/list\";"
				   + "</script>";
		} else {
			result = "<script>"
				   + "alert(\"비밀번호가 틀립니다\");"
				   + "history.back();"
				   + "</script>";
		}
		
		return result;
	}
}

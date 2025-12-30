package com.sist.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.web.service.BoardService;
import com.sist.web.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService bService;
	
	/*
	 *  Spring 5 버전
	 *  	=> 보안 (HttpServletRequest => 사용하지 않는다 (권장))
	 *  			| 요청값, 결과값 전송
	 *  				|		|
	 *  			  매개변수   Model => 전송 객체
	 *  Spring 6 버전 : 도메인 방식
	 *  ----------------------
	 *  				| MVC 단점 : DispatcherServlet이 한 개
	 *  								 | 집중이 된다
	 *  								 | 분산해서 사용 => MSA
	 */
	
	@GetMapping("/board/list")
	public String board_list(@RequestParam(name = "page", required = false) String page, Model model) {
		if(page==null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		List<BoardVO> list = bService.boardListData((curpage * 10) - 10);
		int totalpage = bService.boardTotalPage();
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		
		model.addAttribute("main_html", "board/list");
		return "main/main";
	}
	
	@GetMapping("/board/detail")
	public String board_detail(@RequestParam("no") int no, Model model) {
		BoardVO vo = bService.boardDetail(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "board/detail");
		return "main/main";
	}
	
	@GetMapping("/board/insert")
	public String board_insert(Model model) {
		model.addAttribute("main_html", "board/insert");
		return "main/main";
	}
	
	@PostMapping("/board/insert_ok")
	public String board_insert_ok(@ModelAttribute BoardVO vo) {
		bService.boardInsert(vo);
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/update")
	public String board_update(@RequestParam("no") int no, Model model) {
		BoardVO vo = bService.boardUpdateData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "board/update");
		return "main/main";
	}
	
	@PostMapping("/board/update_ok")
	@ResponseBody // @RestController (vue 연결) => 자체
	public String board_update_ok(@ModelAttribute("vo") BoardVO vo) {
		String res = "";
		
		boolean bCheck = bService.boardUpdate(vo);
		
		if(bCheck == true) {
			res = "<script>"
				+ "location.href=\"/board/detail?no=" + vo.getNo() + "\""
				+ "</script>";
		} else {
			res = "<script>"
				+ "alert(\"Password Fail!\");"
				+ "history.back()"
				+ "</script>";
		}
		
		return res;
	}
	
	@GetMapping("/board/delete")
	public String board_delete(@RequestParam("no") int no, Model model) {
		model.addAttribute("no", no);
		model.addAttribute("main_html", "board/delete");
		return "main/main";
	}
	
	@PostMapping("/board/delete_ok")
	@ResponseBody // @RestController (vue 연결) => 자체
	public String board_delete_ok(@RequestParam("no") int no, @RequestParam("pwd") String pwd) {
		String res = "";
		
		boolean bCheck = bService.boardDelete(no, pwd);
		
		if(bCheck == true) {
			res = "<script>"
				+ "location.href=\"/board/list\""
				+ "</script>";
		} else {
			res = "<script>"
				+ "alert(\"Password Fail!\");"
				+ "history.back()"
				+ "</script>";
		}
		
		return res;
	}
}



























package com.sist.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.web.mapper.UsersMapper;
import com.sist.web.vo.UserRolesVO;
import com.sist.web.vo.UsersVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final PasswordEncoder pEncoder;
	private final UsersMapper mapper;
	
	@GetMapping("/")
	public String main_page() {
		return "main";
	}
	
	@GetMapping("/login")
	public String login_page() {
		return "login";
	}
	
	@GetMapping("/join")
	public String join_page() {
		return "join";
	}
	
	@PostMapping("/join")
	public String join_ok_page(@ModelAttribute("vo") UsersVO vo) {
		vo.setPassword(pEncoder.encode(vo.getPassword()));
		// pwd.match()
		mapper.usersInsert(vo);
		UsersVO dbvo = mapper.findByUsername(vo.getUsername());
		
		UserRolesVO rvo = new UserRolesVO();
		rvo.setUser_id(dbvo.getId());
		rvo.setRole_name("ROLE_USER");
		mapper.userRoleInsert(rvo);
		
		return "redirect:/";
	}
	
	@GetMapping("/all")
	public String all_page() {
		return "all";
	}
	
	@GetMapping("/admin")
	public String admin_page() {
		return "admin";
	}
	
	@GetMapping("/user")
	public String user_page(@AuthenticationPrincipal UserDetails userDetail, Model model) {
		
		model.addAttribute("id", userDetail.getUsername());
		model.addAttribute("roles", userDetail.getAuthorities());
		return "mypage";
	}
}

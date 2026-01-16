package com.sist.web.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sist.web.mapper.UsersMapper;
import com.sist.web.vo.UsersVO;

import lombok.RequiredArgsConstructor;

/*
 * 	1. SecurityConfig.java : 접근 권한 (URL) => 로그인 / 로그아웃 / 자동 로그인
 * 			|
 * 		사용자 정보 읽기 CustomUserDetailService
 * 		로그인 성공     LoginSuccessHandler ==> session에 저장
 * 		로그인 실패		LoginFailHandler
 * 		SessionUtil
 * 		SessionListener => 중복 / 자동으로 Session 해제
 * 						   ---   ----------------- 로그아웃 / 브라우저 종료
 * 	2. WebSockekConfig.java : 등록 (URL)
 * 			| UserSessionRegistry.java : 접속자 목록
 * 			| WebSocketRegistry.java : /
 * 			| WebSocketConfigEventListener : 일대일 / 전체
 * 	-----------------------------------------------------------------
 * 	3. 화면 처리
 * 		=> RouterController
 * 		=> ChatController
 * 			@MessageMapping() / => 화면 변경
 * 				| 채팅
 * 	4. 데이터 전송 : sender / receiver / message => DTO (VO)
 * 
 * 		=> JSP : Pinia 처리
 * 					=> Kakao
 */

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
	private final UsersMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UsersVO user = mapper.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Username을 찾을 수 없습니다");
		}
		
		List<String> roles = mapper.findRolesByUserId(user.getId());
		
		// 권한 관련
		Set<GrantedAuthority> grantedAuthority = new HashSet<>();
		for (String role : roles) {
			grantedAuthority.add(new SimpleGrantedAuthority(role));
		}
		
		// 사용자의 모든 정보 저장 => session에 저장
		return new User(user.getUsername(), user.getPassword(), user.getEnabled() == 0 ? false : true,
				true, true, true, grantedAuthority);
	}
	
}

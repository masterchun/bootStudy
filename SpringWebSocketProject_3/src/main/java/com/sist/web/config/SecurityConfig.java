package com.sist.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.sist.web.security.LoginFailHandler;
import com.sist.web.security.LoginSuccessHandler;
import com.sist.web.service.CustomUserDetailService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	/*
	 * 	http => url의 접근 허용
	 * 	=> requestMatchers('/login', '/admin')
	 * 	=> permitAll() => 전체 허용
	 * 	=> authenticated() => 인증받은 사용자 => login
	 * 	=> denyAll() => 모두 거절 (접근 거부)
	 * 	=> hasRole() => 1개 지정
	 * 		ROLE_ADMIN / ROLE_USER
	 * 	=> hasAnyRole() => 2개 지정
	 * 
	 * 	csrf : 공격자가 인증된 브라우저에서 쿠키 / 세션을 활용해서 웹서버에서 사용
	 */
	private final CustomUserDetailService userDetailService;
	private final LoginSuccessHandler loginSuccessHandler;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 권한 제시
		http
			// 필수 =? WebSocket 사용
			.csrf(csrf -> csrf.disable())
			// 접근 권한
			.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/join", "/login", "/chat-ws/**").permitAll()
					.requestMatchers("/user").authenticated()
					.requestMatchers("/admin").hasRole("ADMIN")
					.anyRequest().permitAll())
			// 세션 기반 로그인 => state(저장 상태) : cookie (JWT)
			.sessionManagement(
					session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
					)
			// => SNS 로그인
		// 로그인
			.formLogin(form -> form.loginPage("/login") // 사용자 정의
					.loginProcessingUrl("/login_process") // 자체 로그인 처리
					.defaultSuccessUrl("/", true)
					.failureHandler(loginFailHandler())
					.successHandler(loginSuccessHandler))
					// 로그인 성공시 처리 => 중복 로그인 제어 => .successHandler()
		// 로그아웃
			.logout(logout -> logout.logoutSuccessUrl("/")
					.deleteCookies("remember-me")) // 로그아웃 쿠키 삭제
			// remember-me : 쿠키 키 값
		// 자동 로그인
			.rememberMe(remember -> remember.key("remember-my-security-key")
					.tokenValiditySeconds(60 * 60 * 24)
					.userDetailsService(userDetailService));
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 로그인 실패시 처리
	@Bean
	public AuthenticationFailureHandler loginFailHandler() {
		return new LoginFailHandler();
	}
	
	// 로그인 성공시 처리 => 자동으로 session에 등록
	//                        stomp
}

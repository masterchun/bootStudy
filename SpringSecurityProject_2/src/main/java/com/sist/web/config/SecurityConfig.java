package com.sist.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.sist.web.service.CustomUserDetailService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomUserDetailService userDetailService;

	// 재정의 => 권한에 따라 => 접근 여부, 로그인 / 로그아웃 / 자동 로그인
	/*
	 * csrf Cross site Request forgery => 공격자가 인증된 브라우저에서 저장된 쿠키나 세션 정보를 활용해서 웹서버에
	 * 사용자가 의도하지 않는 요청을 전달 => 위조 방지 : JWT => 일반 보안 => csrf.disable()
	 * 
	 * authorizeHttpRequests : 인증, 인가가 필요한 URL 지정
	 * 
	 * anyrequest.permitall => 게스트 포함
	 * 
	 * requestMatchers : URL 마다 권한 지정 anyRequest : requestMatchers 지정된 url 외의 처리 |
	 * denyAll(), permitAll() => 누구나 접근 가능 => 403 : 접근 거부 => authenticated() => 해당
	 * URL에 접근시에 인증을 거쳐야 한다 -- 확인 (로그인) / 인가는 허락 => hasRole("ROLE_ADMIN") =>
	 * hasAnyRole("", "", "")
	 */

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/").permitAll().requestMatchers("/user")
						.authenticated().requestMatchers("/admin").hasRole("ADMIN").anyRequest().permitAll())
				// 로그인
				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/", true)
						.failureHandler(loginFailHandler()))
				// 로그아웃 => invalidate() 자동으로 해줌, cookie는 사용자가 삭제해야 한다
				.logout(logout -> logout.logoutSuccessUrl("/")).rememberMe(remember -> remember.key("remember-me-key")
						.tokenValiditySeconds(60 * 60 * 24 * 7).userDetailsService(userDetailService));
		return http.build();
	}

	// 5 -> 비밀번호 암호화 => 회원 가입시(암호화) / 로그인(복호화)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationFailureHandler loginFailHandler() {
		return new LoginFailHandler();
	}
}

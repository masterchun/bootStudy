package com.sist.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 		interface
 * 			= abstract 메소드 => 구현이 안된 메소드
 * 			= default 메소드 => 구현이 된 메소드 (필요에 의해서 재정의)
 * 
 * 			* 기억 => interface는 변수 / 메소드가 public만 사용한다
 * 			public interface A {
 * 				// 변수 = 상수형
 * 				int a; => 오류 발생
 * 				(public static final) int a = 10;
 * 				(public abstract) void aaa();
 * 
 * 				default public void bbb(); +> 오류
 * 				default public void bbb() { }
 * 			}
 * 		user 요청 ==== DispatcherServlet
 * 						|
 * 					HandlerMapping
 * 						| ------------- interceptor
 * 						  preHandle
 * 					@Controller : 개발자 (요청 처리) : Model
 * 						| ------------- interceptor
 * 						  postHandle
 * 					ViewResolver
 * 						| ------------- interceptor
 * 						  afterCompletion
 * 					  View : 개발자 (화면 출력) : View
 * 
 * 		preHandle
 * 			=> 컨트롤러의 메소드 수행 전
 * 			   인증 / 인가 검증
 * 			   로깅 시작
 * 			   요청값 검증
 * 			   ----------- 자동 로그인 처리 / 권한 부여
 * 						   채팅방 정보 읽기 / 접속자 정보
 * 		postHandle
 * 			=> 컨트롤러가 실행 : 화면 변경 전 => 화면 UI
 * 			=> 권한 / 로그인 여부 확인
 * 		afterCompletion
 * 			=> View 완료 => 렌더링 이후
 * 			=> 리소스 정리
 * 			   DB 닫기, 파일 닫기, 예외 로그 처리
 */
@Component
public class BoardInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("preHandle() call...");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle call...");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion call...");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}

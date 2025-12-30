package com.sist.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.service.EmpService;
import com.sist.web.vo.EmpVO;

import lombok.RequiredArgsConstructor;

/*
 * 		1. 교재
 * 			1) 스프링 개발 / 환경 소개
 * 				= 스프링 프레임워크의 역사, 철학
 * 								--- 세미나 => 로드 존슨 (느슨한 결합) : 오픈 소스에 개발
 * 								--- 2.5 : POJO (단독 클래스) : 인터페이스 / 상속없이 처리
 * 								---		  --------------- 관리자 (Spring)
 * 				= 아키텍쳐 구조
 * 					MVC
 * 						=> 사용자 요청 => DispatcherServlet => HandlerMapping
 * 																	| => 개발자가 직접 만들어서 처리
 * 															사용자 정의 클래스 찾기
 * 															@Controller : 기능 및 요청 처리는 사용자가
 * 																=> 데이터 전송
 * 																=> html / jsp 파일
 * 																=> 1) 데이터 전송
 * 																		forward
 * 																		return "폴더/파일명"
 * 																=> 2) 데이터 전송없이 화면 변경
 * 																		redirect
 * 																		return "redirect:..."
 * 															@RestController
 * 																=> 데이터만 전송
 * 																=> 다른 프로그램과 연동
 * 																	JS / Kotlin
 * 				= 스프링 부트 등장 / 특징
 * 					# 스프링 부트 => Spring을 쉽게 쓰기 위한 도구
 * 								  새로운 기능이 아니라 설정을 대신하는 프레임워크
 * 					# 스프링 프레임워크보다 빠른 개발이 가능
 * 					# 설정을 최소화
 * 					# 내장 톰캣을 사용 => 서버 설치가 필요없다
 * 					  -------> CI / CD
 * 					# main 메소드로 실행 => 단독 실행이 가능 (배포 => jar, war)
 * 																  | jsp
 * 															 | ThymeLeaf
 * 					# Starter => 의존성 제공 (라이브러리, 설정)
 * 					# XML 최소화 (필요시에는 Java Config)
 * 											| JWT 보안
 * 		--------------------------------------------------------------------------------------------
 * 
 * 		2. Spring-Boot JSP 사용
 * 			# JSP => main
 * 					   |
 * 					webapp
 * 					   |
 * 					WEB-INF
 * 					   | JSP 설정 위치 => 지원하지 않는다
 * 						=> TymeLeaf 권장
 * 						   ------------
 * 							Boot Server + ThymeLeaf
 * 							Boot Server + React
 * 			# Controller를 통해서 접근이 가능
 * 			# JSP 위치
 * 				=> webapp => WEB-INF에 존재
 * 				=> Spring-Boot에서 JSP가 번거로운 이유 : 권장하지 않는다
 * 				=> application.yml
 * 					: prefix / suffix
 * 						| 경로명	  | 확장자 => .jsp
 * 						| WEB-INF/jsp/
 * 		--------------------------------------------------------
 * 
 * 		RestFul
 * 			=> @GetMapping : SELECT
 * 			=> @PostMapping : INSERT
 * 			=> @PutMapping : UPDATE
 * 			=> @DeleteMapping : DELETE 
 * 		---------------------------------------------------------
 * 
 * 		# 보안 강조
 * 			HttpServletRequest
 * 				=> 데이터 전송
 * 					Model => 전송 객체
 * 				=> 요청 값 받기
 * 					매개 변수
 * 			-----------------------
 * 			request : Cookie 읽기
 * 			HttpServletResponse
 * 				Cookie 전송 / 다운로드시에 주로 사용
 * 		# Controller
 * 			=> DispatcherServlet => 위임 개발자가 처리
 * 			=> 리턴형
 * 				String / void
 * 							| 다운로드시에 처리
 * 				| 전송할 파일 지정
 * 			=> 매개변수 : 사용자 요청값 / 내장 객체 => 순서는 상관없다
 * 					   커맨드 객체 사용 : VO 단위로 값을 받는 경우
 * 						게시판 / 댓글 / 회원가입 => <form>
 * 				@RequestParam => getParameter()
 * 				@ModelAttribute : VO 단위
 * 				@RequestBody ==> JSON 객체 변환
 * 				HttpSession / HttpServletRequest / HttpServletResponse
 * 			=> ?no=1&page=1
 * 				---- ---
 * 					\	\
 * 				(int no, int page)
 * 			=> ModelAttribute
 * 				<input type="text" name="name">
 * 				<input type="text" name="subject"> => VO에는 String name, subject
 * 
 * 			=> 화면 출력
 * 				EL / JSTL
 * 					 ----
 * 					 <c:forEach> <c:if> <c:choose> <c:forTokens>
 * 					 <c:set>
 * 				| ${key} => request.getAttribute(key)
 * 				| ${sessionScope.key} => session.getAttribute(key)
 * 				| 연산자
 * 					= 산술 연산자 (+, -, *, /, %)
 * 							   --       --
 * 							덧셈만 가능     정수 / 정수 = 실수
 * 							문자열 결합 : : +=
 * 							=> null이 있는 경우 => 0으로 취급
 * 					= --, !=, <, >, <=, >=
 * 						=> 비교연산자 : 문자열 / 날짜 비교가 가능
 * 					= &&, ||
 * 					= 삼항 연산자 ? : 
 * 			=> Session / Cookie
 * 				서버		클라이언트 브라우저
 * 							=> 자동 로그인 / 방문지 => 직접 생성
 * 							=> JWT
 * 				=> 로그인 처리 / 장바구니 => 내장 객체
 * 					일반 Security
 * 		------------------------------------------------------------------
 * 
 * 		DI / AOP / MVC
 * 		
 * 		DI => 클래스와 클래스의 관계
 * 				객체 생성 / 객체 소멸
 * 				1) XML (framework)
 * 					한개 객체 생성 <bean id="" class="">
 * 								생성시에 필요한 데이터 추가
 * 								p:변수 => setter DI
 * 								c:변수 => constructor DI
 * 					패키지별 객체 생성
 * 					<context:component-scan basepackage="">
 * 					=> @ComponentScan(basePackages="")
 * 				2) 어노테이션 (boot)
 * 					=> @Component : AOP 클래스, OpenAPI, WebSocket
 * 					=> @Repository : 데이터베이스 변동
 * 									 ------------ JPA / MyBatis
 * 					=> @Service : 의존성 낮은 프로그램
 * 									Repository를 여러개 사용 / 기능 추가
 * 					=> @Controller : 화면 제어 / 데이터 전송
 * 					=> @RestController : 데이터만 전송 (JSON)
 * 					=> @ControllerAdvice / @RestControllerAdvice
 * 							|						|
 * 							-------------------------
 * 										| 공통 예외 처리
 * 			사용자 요청 = @Controller = @Service = @Repository
 * 			
 * 			AOP
 * 				횡단 지향적 프로그램 => 객체 지향 프로그램의 단점 보완
 * 										|
 * 									공통으로 적용 => 메소드 => 호출
 * 					|
 * 				CallBack => 자동 호출
 * 							-------
 * 							1. PointCut : 어떤 메소드 호출시 적용
 * 								execution(* 패키지명.클래스명.메소드())
 * 										  |					 |
 * 										리턴형				 ..
 * 															----
 * 														 매개변수의 개수와 상관없다
 * 														매개변수의 데이터형
 * 							2. JoinPoint : 메소드의 어떤 위치
 * 								public String method() {
 * 									@Before
 * 									try {
 * 										---------------------------- @Around (before)
 * 
 * 										---------------------------- @Around (after)
 * 									} catch (Exception e) {
 * 										@AfterThrowing
 * 									} finally {
 * 										@After
 * 									}
 * 									return ""; => @AfterReturning
 * 								}
 * 								해당 메소드를 통합해서 호출 => 프록시
 * 								------------- 위빙
 * 								public void getConnection()
 * 								public void disConnection()
 * 								public void select() {
 * 								}
 * 								---------------------------------
 * 								public void select() {
 * 									getConnection()
 * 									처리
 * 									disConnection()
 * 								}
 * 			=> ORM
 * 				1. MyBatis
 * 					= XML
 * 					= Annotation
 * 					= XML + Annotation
 * 						<select> @Select
 * 						<insert> @Insert ...
 * 				2. JPA
 * 					= Annotation
 * 					= 메소드 규칙 이용
 * 					= Annotation + 메소드 규칙 이용
 * 						@Query("") findBy컬럼명명령문
 * 								   findByNameLike
 * 								   findByName => =f
 * 		---------------------------------------------------------------
 * 
 * 		= 마이크로 서비스 MSA
 * 		= 스프링 클라우드
 * 		= 리액티브 프로그래밍 : WebFlux
 * 		= 보안 : JWT
 * 		= 카프카 / 통합
 * 		= React
 * 		= 코틀린 개발
 * 		= Spring AI
 */

@Controller
@RequiredArgsConstructor
public class MainController {
	private final EmpService eService;
	
	@GetMapping("/")
	public String main_main(Model model) {
		model.addAttribute("msg", "Hello Spring Boot JSP");
		return "main";
	}
	
	@GetMapping("/emp/list")
	public String emp_list(Model model) {
		List<EmpVO> list = eService.empListData();
		model.addAttribute("list", list);
		return "emp/list";
	}
}





































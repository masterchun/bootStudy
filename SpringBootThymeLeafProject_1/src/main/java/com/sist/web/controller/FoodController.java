package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.service.EmpService;
import com.sist.web.service.FoodService;
import com.sist.web.vo.EmpVO;
import com.sist.web.vo.FoodVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 		AOP : 횡단지향적 프로그램
 * 			=> OOP를 보완 (불가능한 프로그램 제공)
 * 			=> OOP에서는 자동 호출이 불가능
 * 			   --- 소스 중복이 있는 경우
 * 				| 메소드 (클래스 한 곳) / 여러개의 클래스 사용 시
 * 										=> 클래스를 이용
 * 				----------------------------- 공통 모듈
 * 			=> AOP애는 자동 호출 (Callback : 시스템에서 자동 호출되는 메소드)
 * 			   ---- 공통 사용시에 지정
 * 				1. 어떤 메소드에서 호출  여부 => PointCut
 * 				2. 메소드의 어떤 영역에서 호출할지 여부 => JoinPoint
 * 					JoinPoint
 * 						= @Before : try 진입 전
 * 									public void display() {
 * 										@Before
 * 										try {
 * 										}
 * 									}
 * 						= @After : finally에서 호출 => 무조건 수행
 * 						= @After-Throwable : catch에서 호출
 * 						= @After-Returning : 정상수행 => return이 된 상태
 * 						= 코드
 * 							--------- 1
 * 								코드
 * 							--------- 2 => @Around : 트랜잭션 / 보안 / 로그 파일
 * 						JoinPoint / PointCut => Advice
 * 												------ 여러개 통합 : Aspect
 *						=> 클래스의 기능별 분리
 *							유지보수가 편리하다
 *		= MVC
 *			1. 클라이어트 요청 발생
 *				<a> <form> : html => JS와 호환성이 떨어진다
 *			2. 톰캣(내장)에 전송
 *			3. 톰캣에서 DispatcherServlet으로 전달
 *				미리 등록
 *			4. DispatchServlet
 *					|
 *				Controller 중에 => URI를 가지고 있는 메소드 찾기
 *									=> @GetMapping / @PostMapping
 *				------------------------- HandlerMapping
 *			5. 데이터를 전송할 JSP / HTML이 지정 => return
 *				=> 데이터 전송 : return "폴더/파일명";
 *					=> HttpServletRequest / Model (권장)
 *				=> 기존의 설정된 Mapping 호출 : return "redirect:URI명";
 *			6. 화면 출력 => 출력에 필요한 데이터 전송 : ViewResolver
 *							=> 경로명 / 확장자 전송
 *								prefix suffix
 *			7. 화면 UI
 *				= JSP
 *				= ThymeLeaf
 *				= React / Vue
 *		= JDBC
 *			= ORM
 *			  --- MyBatis / JPA (Hibernate) => dataSet
 *					| XML에서 주로 사용
 *		-------------- 스프링의 기본
 *
 *		ThymeLeaf
 *		---------
 *			주요 기능
 *				=> Vue와 비슷 => 태그 안에서 제어문
 *					<div v-for=""> => <div th:each="vo:${list}">
 *										=> 자바에서 사용하는 데이터를 그대로 사용
 *				=> 서버 사이드 렌더링 엔진
 *				   ------------- HTML로 변환
 *				=> 화면 UI => Front
 *				=> 자바와 호환성
 *				=> JSP보다 현대적이고 구조적
 *				=> HtML을 브라우저에서 실행시 정상적으로 수행
 *				=> 권장 상태
 *			----------------------------------------------
 *							ThymeLeaf			JSP
 *			----------------------------------------------
 *			HTML 파일을        실행 가능				불가능 
 *			바로 실행 여부
 *			----------------------------------------------
 *			Spring MVC		 매우 우수				제한적
 *			통합
 *			----------------------------------------------
 *			템플릿 문법			  현대식				구식
 *			----------------------------------------------
 *			속도				   빠름				느림
 *			----------------------------------------------
 *			현재 트렌드			아주 많이 사용			사용하지 않는다
 *												** 유지 보수
 *							Vue / React
 *			----------------------------------------------
 *
 *			----------------------------------------------
 */
@Controller
public class FoodController {
	@Autowired()
	private EmpService eService;
	
	@Autowired
	private FoodService fService;
	
	@GetMapping("/")
	public String main_main(Model model) {
		List<EmpVO> list = eService.empListData();
		model.addAttribute("list", list);
		
		return "main";
	}
	
	@GetMapping("/food/list")
	public String food_list(@RequestParam(name = "page", required = false) String page, Model model) {
		if(page == null) page = "1";
		int curpage = Integer.parseInt(page);
		int rowSize = 12;
		int start = (rowSize * curpage) - (rowSize - 1);
		int end = rowSize * curpage;
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<FoodVO> list = fService.foodListData(map);
		int totalpage = fService.foodTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((curpage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curpage - 1) / BLOCK * BLOCK) + BLOCK;
		if(endPage > totalpage) endPage = totalpage;
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "food/list"; // forward
	}
	
	@GetMapping("/food/detail_before")
	public String food_detail_before(@RequestParam(name = "fno", required = false) int fno, 
			HttpServletResponse response, 
			RedirectAttributes ra) {
		// response = Cookie 전송 , 다운로드
		Cookie cookie = new Cookie("food_" + fno, String.valueOf(fno));
		// cookie는 저장값 => String
		// 1. 저장 위치 지정
		cookie.setPath("/");
		// 2. 저장 시간
		cookie.setMaxAge(60*60*24);
		// 3. 해당 브라우저로 전송
		response.addCookie(cookie);
		ra.addAttribute("fno", fno);
		// redirect에서 다른 파일로 값을 전송 : RedirectAttributes
		// forward : Model
		return "redirect:/food/detail";
		// sendRedirect 화면에 보여주지 않는 작업을 할 때 redirect
	}
	
	@GetMapping("/food/detail")
	public String food_detail(@RequestParam(name = "fno", required = false) int fno, Model model) {
		FoodVO vo = fService.foodDetailData(fno);
		model.addAttribute("vo", vo);
		
		List<FoodVO> list = fService.foodTop10();
		for(FoodVO fvo:list) {
			String addr = fvo.getAddress();
			addr = addr.substring(0, addr.indexOf(" "));
			fvo.setAddress(addr.trim());
		}
		model.addAttribute("list", list);
		
		return "food/detail";
	}
}
































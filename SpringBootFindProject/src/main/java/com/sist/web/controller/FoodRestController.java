package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.FoodService;
import com.sist.web.vo.FoodVO;

import lombok.RequiredArgsConstructor;

/*
 * 		--------------------------------------------------
 * 					ThymeLeaf			Vue3
 * 		--------------------------------------------------
 * 		렌더링		서버 렌더링				클라이어트 렌더링
 * 		검색 엔진		매우 뛰어나다			=> 서버 렌더링 사용 (Nuxt)
 * 		데이터 바인딩 	단방향				양방향 => AngularJS / React\
 * 		속도			서버처리 중심			브라우저 중심
 * 					Back-End			Front-End
 * 		사용처		관리페이지				| 모바일 => 동적
 * 					| 공공기업 사이트		| 페이징 / 검색 / 예약 / 결제
 * 										| 채팅 / AI (GPT)
 * 		
 * 		1. DOM (ML => 저장될 때 트리 형태로 저장)
 * 			Document Object Model
 * 			Vue3 / React
 * 			 |	 	 |
 * 			 ---------
 * 				 |
 * 			가상 돔 + 반응형 => 동작
 * 		= 가상 돔
 * 			브라우저 화면 직접 실행하는 실제 돔이 아니다
 * 			가상 메모리에 저장 => 변경시마다 실제 돔에 전송
 * 				|				|
 * 				----------------- diff (비교)
 * 			속도가 빠르게 / 깜빡이는 효과가 없이
 * 		= 컴파일 = 렌더링을 실시간 수행
 * 			=> 데이터만 변경
 * 
 * 		2. MVVM (Model - View - ViewModel)
 * 		   MVC (Model - View - Controller)
 * 			=> 요청 = Controller = Model = Controller = View
 * 						|			|
 * 					 요청 받기       
 * 			=> 요청 = Model <----> ViewModel <----> View
 * 					   |			 |				|
 * 					데이터 관리		  데이터 변경   	  화면 출력
 * 								  데이터 처리		  mount 영역
 * 					   |			 |				=> <template>
 * 					data()		  created()
 * 								  mounted()
 * 								  updated()
 * 								  unmounted()
 * 								  methods:{}
 * 			Model : 데이터의 상태 관리 (state)
 * 					Pinia / VueX ==> 저장 (store)
 * 			View : HTML을 제어
 * 			ViewModel : 양방향 바인딩, 반응성 관리, 이벤트 처리
 * 
 * 			*** Model이 변경이 되면 ViewModel ==> HTML 반영
 * 				| 설정된 변수가 변경시
 * 		3. 생명 주기 함수
 * 			created() : Vue 생성시 자동 호출
 * 			mounted() : 브라우저에 HTML이 번역 후 출력이 된 경우
 * 			updated() : 데이터값이 변경된 후
 * 			unmounted() : 화면이 변경
 * 			=> CallBack => Vue 시스템에 의해 자동 호출
 * 
 * 			methods: 사용자 정의 함수 => 자동이 안된다
 * 			*** React / Vue / Next / Node / Nuxt => 기본 코딩은 JavaScript
 * 			*** 자바 / 오라클
 * 			*** JavaScript
 * 
 * 		4. 디렉티브
 * 			v-model : 입력시에 data()에 있는 변수에 값을 설정
 * 				| 양방향
 * 				| react에 없다 => onchange
 * 			v-bind => 속성에 값을 설정 => :
 * 				<img v-bind:src="vo.poster">
 * 				<img :src="vo.poster">
 * 			v-on => 이벤트 처리
 * 			<button v-on:click="함수명"></button>
 * 					=> v-on => @click=""
 * 			v-for : 반복문 => for-each만 지원
 * 			v-if / v-else => v-else-if ...
 * 			=> hide / show
 * 				|
 * 			  v-show="조건:false" v-show="조건:true"
 * 			뉴스 / 날짜 => 오픈 API
 * 			 |	   |
 * 			 -------
 * 				|
 * 			HTML이 포함
 * 			v-text(X) => v-html => html() text() attr()
 * 			v-pre : 있는 그대로 출력
 * 			v-once : 한번만 수행
 * 			v-slot : 컴포넌트에 데이터 전송 => prop: []
 * 		--------------------------------------------------------
 * 		1. Component => CDN
 * 			| => vuex / pinia
 * 		
 * 		기본 패턴
 * 		<script>
 * 			const app = Vue.createApp({
 * 				data() {
 * 					return {
 * 
 * 					}
 * 				},
 * 				mounted() {
 * 					시작과 동시에 데이터 읽기
 * 				},
 * 				methods: {
 * 					사용자 정의, 이벤트
 * 				},
 * 				components: {
 * 				}
 * 				computed: {
 * 				}
 * 			}).mount(제어할 HTML의 영역 설정)
 * 		</script>
 */

@RestController
@RequiredArgsConstructor
public class FoodRestController {
	private final FoodService fService;
	
	@GetMapping("/food/find_vue/")
	public Map food_find_vue(@RequestParam("page") int page, @RequestParam(name = "address", required = false) String address) {
		if(address == null) {
			address ="마포";
		}
		
		Map map = new HashMap();
		int rowSize = 12;
		
		int start = (rowSize * page) - (rowSize - 1);
		int end = rowSize * page;
		
		map.put("start", start);
		map.put("end", end);
		map.put("address", address);
		
		List<FoodVO> list = fService.foodListData(map);
		int totalpage = fService.foodTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((page - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((page - 1) / BLOCK * BLOCK) + BLOCK;
		if(endPage > totalpage) {
			endPage = totalpage;
		}
		
		map = new HashMap();
		map.put("list", list);
		map.put("totalpage", totalpage);
		map.put("curpage", page);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("address", address);
		
		return map;
	}
	
	@GetMapping("/food/detail_vue/")
	public FoodVO food_detail_vue(@RequestParam("fno") int fno) {
		FoodVO vo = fService.foodDetailData(fno);
		return vo;
	}
}

























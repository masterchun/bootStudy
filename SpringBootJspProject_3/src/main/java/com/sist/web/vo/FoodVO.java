package com.sist.web.vo;

import lombok.Data;

/*
 * 		Vue 사용처
 * 			검색 결제 예약 추천 로그인 수정/삭제
 * 			실시간 (채팅)
 * 			================ 일반 JSP
 */

@Data
public class FoodVO {
	private double score;
	private int fno, hit;
	private String name, type, phone, address, theme, 
		price, time, parking, poster, images, content;
}

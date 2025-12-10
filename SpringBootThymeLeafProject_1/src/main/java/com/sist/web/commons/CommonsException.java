package com.sist.web.commons;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 공통으로 적용되는 예외처리
@ControllerAdvice
public class CommonsException {
	@ExceptionHandler(Exception.class) // 예외 처리
	public void exception(Exception e) {
		System.out.println("===== 에러 발생 =====");
		System.out.println();
		e.printStackTrace();
		System.out.println("===================");
	}
	
	@ExceptionHandler(Throwable.class) // 에러 처리
	public void throwable(Throwable e) {
		System.out.println("===== 에러 발생 =====");
		System.out.println();
		e.printStackTrace();
		System.out.println("===================");
	}
}

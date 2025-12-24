package com.sist.web.commons;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonsException {

	@ExceptionHandler(Exception.class)
	public void exception(Exception e) {
		System.out.println("=========== Exception 에러 ============");
		System.out.println();
		e.printStackTrace();
		System.out.println();
	}
	
	@ExceptionHandler(Throwable.class)
	public void throwable(Throwable e) {
		System.out.println("=========== Exception 에러 ============");
		System.out.println();
		e.printStackTrace();
		System.out.println();
	}
}

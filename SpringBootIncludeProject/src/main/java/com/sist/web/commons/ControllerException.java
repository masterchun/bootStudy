package com.sist.web.commons;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 모든 요청 기능 처리 == catch
public class ControllerException {
	@ExceptionHandler(Exception.class)
	public void exception(Exception e) {
		System.out.println("========================= Controller에서 예외 발생 ============");
		System.out.println();
		e.printStackTrace();
		System.out.println("==================================================================");
	}
	
	@ExceptionHandler(Throwable.class)
	public void throwable(Throwable t) {
		System.out.println("========================= Controller에서 예러 발생 ============");
		System.out.println();
		t.printStackTrace();
		System.out.println("==================================================================");
	}
}

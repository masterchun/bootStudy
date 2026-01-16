package com.sist.web.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.http.HttpSession;

// Session 강제 종료
public class SessionUtils {
	// 모든 세션 저장 장소
	private static final Map<String, HttpSession> STORE = new ConcurrentHashMap<>();
	// 비동기 => 충돌 최소화, 멀티 쓰레드의 안전성 확보를 위한 클래스
	// => Set => hashcode를 재정의
	// static은 공통으로 사용하는 메소드 => 모든 유저들이 한 개의 SessionUtils만 사용
	
	// 정상 수행
	public static void add(HttpSession session) {
		STORE.put(session.getId(), session);
	}
	
	// 강제 종료
	public static void remove(HttpSession session) {
		STORE.remove(session);
	}
	
	public static void invalidate(String sessionId) {
		HttpSession session = STORE.get(sessionId);
		if (session != null) {
			session.invalidate();
			STORE.remove(sessionId);
		}
	}
}

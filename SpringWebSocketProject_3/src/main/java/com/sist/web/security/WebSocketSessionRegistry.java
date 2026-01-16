package com.sist.web.security;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class WebSocketSessionRegistry {
	private final Map<String, Set<String>> map = new ConcurrentHashMap<>();
	
	public void register(String userid, String sessionId) {
		map.computeIfAbsent(userid, k -> ConcurrentHashMap.newKeySet()).add(sessionId);
		// computeIfAbsent : 없는 경우 생성하지 않고 기존의 데이터를 첨부
		/*
		 *  if (!map.containsKey(key)) { 중복이 없는 경우
		 *  	map.put(key, new HashSet<>)
		 *  }
		 *  
		 *  map.get(key().add(value)
		 */
	}
	
	public void unregister(String userid, String sessionId) {
		Set<String> set = map.get(userid);
		
		if (set != null ) {
			set.remove(sessionId);
			if (set.isEmpty()) {
				map.remove(userid);
			}
		}
	}
	
	public Set<String> getSession(String userid) {
		// 목록 => 1:1 => 
		return map.getOrDefault(userid, Set.of());
	}
}

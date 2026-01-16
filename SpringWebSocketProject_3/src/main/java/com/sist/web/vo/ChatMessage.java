package com.sist.web.vo;

import lombok.Data;

@Data
public class ChatMessage {
	private String type; // 전체 / 1:1
	private String sender;
	private String receiver;
	private String message;
	private String time;
}

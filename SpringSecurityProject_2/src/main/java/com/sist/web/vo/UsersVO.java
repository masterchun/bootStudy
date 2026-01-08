package com.sist.web.vo;

import lombok.Data;

@Data
public class UsersVO {
	private int id, enabled;
	private String username, password;
}

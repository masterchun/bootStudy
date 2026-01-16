package com.sist.web.vo;

import lombok.Data;

@Data
public class UsersVO {
	private String username, password;
	private int id, enabled;
}

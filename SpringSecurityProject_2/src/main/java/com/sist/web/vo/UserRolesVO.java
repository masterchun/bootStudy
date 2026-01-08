package com.sist.web.vo;

import lombok.Data;

@Data
public class UserRolesVO {
	private int id, user_id;
	private String role_name;
}

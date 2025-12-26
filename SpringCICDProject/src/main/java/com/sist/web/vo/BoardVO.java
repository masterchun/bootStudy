package com.sist.web.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private Date regdate;
	private int no, hit;
	private String name, subject, content, pwd, dbday;
}

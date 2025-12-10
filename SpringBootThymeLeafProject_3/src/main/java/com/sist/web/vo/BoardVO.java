package com.sist.web.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO {
	private Date regdate;
	private int no, hit, group_id, group_step, group_tab, root, depth;
	private String name, subject, content, pwd, dbday;
}

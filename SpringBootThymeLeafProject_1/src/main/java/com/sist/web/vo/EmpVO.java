package com.sist.web.vo;

import java.util.Date;

import lombok.Data;

@Data
public class EmpVO {
	private Date hiredate;
	private int empno, sal, deptno, mgr, comm;
	private String ename, job, dbday;
}

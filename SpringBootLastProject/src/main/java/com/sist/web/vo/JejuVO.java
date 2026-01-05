package com.sist.web.vo;

import lombok.Data;

@Data
public class JejuVO {
	private int no, contentid, contenttype, hit;
	private String title, image1, image2, address;
	private double x, y;
}

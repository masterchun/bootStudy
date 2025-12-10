package com.sist.web.vo;

import lombok.Data;

@Data
public class FoodVO {
	private double score;
	private int fno, hit;
	private String name, type, phone, address, theme, price, time, parking, poster, images, content;
}

package com.sist.web.vo;

import lombok.Data;

@Data
public class MusicVO {
	private int no, cno, rank, idcrement, hit;
	private String title, singer, album, poster, state, key;
}

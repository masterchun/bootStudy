package com.sist.web.service;

import java.util.List;

import com.sist.web.vo.CampingVO;

public interface CampingService {
	public List<CampingVO> campingListData(int start);
	
	public int campingTotalPage();
}

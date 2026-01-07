package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.FoodVO;

public interface FoodService {
	public List<FoodVO> foodListData(Map<String, Object> map);
	public int foodTotalPage();
	public FoodVO foodDetailData(Map<String, Object> map);
	public List<FoodVO> foodFindData(Map<String, Object> map);
	public int foodFindTotalData(Map<String, Object> map);
}

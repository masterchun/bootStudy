package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.FoodVO;

@Mapper
@Repository
public interface FoodMapper {
	public List<FoodVO> foodListData(Map map);
	
	public int foodTotalPage();
	
	public void foodHitIncrement(int fno);
	
	public FoodVO foodDetailData(int fno);
	
	public List<FoodVO> foodTop10();
}

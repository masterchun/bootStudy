package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.FoodVO;

@Mapper
@Repository
public interface FoodMapper {
	public List<FoodVO> foodListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*) / 12.0) FROM menupan_food")
	public int foodTotalPage();
	
	public FoodVO foodDetailData(int fno);
	
	@Update("UPDATE menupan_food SET hit = hit + 1 WHERE fno = #{fno}")
	public void foodHitIncrement(int fno);
}

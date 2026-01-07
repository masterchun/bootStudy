package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.FoodVO;

@Mapper
@Repository
public interface FoodMapper {
	// 반복이 많은 경우 => 재사용 => ERP
	@Select(value = "{CALL foodListData(#{pStart, mode=IN, javaType=java.lang.Integer}, "
			      + "#{pResult, mode=OUT, jdbcType=CURSOR, resultMap=foodMap})}")
	@Options(statementType = StatementType.CALLABLE)
	public List<FoodVO> foodListData(Map<String, Object> map);
	
	@Select("SELECT CEIL(COUNT(*) / 12.0) FROM menupan_food")
	public int foodTotalPage();
	
	@Select(value = "{CALL foodDetailData(#{pNo, mode=IN, javaType=java.lang.Integer}, "
	      	      + "#{pResult, mode=OUT, jdbcType=CURSOR, resultMap=detailMap})}")
	@Options(statementType = StatementType.CALLABLE)
	public List<FoodVO> foodDetailData(Map<String, Object> map);
	
	@Update("UPDATE menupan_food SET "
		  + "hit = hit + 1 "
		  + "WHERE fno = #{fno}")
	public void foodHitIncrement(int fno);
	
	public List<FoodVO> foodFindData(Map<String, Object> map);
	
	public int foodFindTotalData(Map<String, Object> map);
}

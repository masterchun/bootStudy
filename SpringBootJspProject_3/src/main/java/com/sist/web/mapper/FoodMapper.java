package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.FoodVO;

@Mapper
@Repository
public interface FoodMapper {
	public List<FoodVO> foodListData(int start);
	
	@Select("SELECT CEIL(COUNT(*) / 12.0) FROM menupan_food")
	public int foodTotalPage();
	
	@Update("UPDATE menupan_food SET hit = hit + 1 WHERE fno = #{fno}")
	public void foodHitIncrement(int fno);
	
	public FoodVO foodDetailData(int fno);
}

/*
 * 	@Controller
 * 		=> 매개변수
 * 			int / String => 그대로 처리
 * 		=> 체크박스
 * 			String[]
 * 		=> <input type="text" name="name[0]">
 * 			<input type="text" name="name[1]">
 * 			<input type="text" name="name[2]">
 * 			<input type="text" name="name[3]">
 * 			=> List 파일 같은 거
 *
 */
 
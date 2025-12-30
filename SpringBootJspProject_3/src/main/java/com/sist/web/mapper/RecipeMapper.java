package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.RecipeDetailVO;
import com.sist.web.vo.RecipeVO;

@Mapper
@Repository
public interface RecipeMapper {
	@Select("SELECT no, title, poster, chef "
		  + "FROM recipe "
		  + "WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipedetail) "
		  + "ORDER BY no ASC "
		  + "OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY")
	public List<RecipeVO> recipeListData(int start);
	
	@Select("SELECT CEIL(COUNT(*) / 12.0) FROM recipe "
		  + "WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipedetail)")
	public int recipeTotalPage();
	
	@Update("UPDATE recipe SET hit = hit + 1 WHERE no = #{no}")
	public void recipeHitIncrement(int no);
	
	@Select("SELECT no, poster, title, chef, chef_poster, chef_profile, "
		  + "info1, info2, info3, content, foodmake "
		  + "FROM recipedetail "
		  + "WHERE no = #{no}")
	public RecipeDetailVO recipeDetailData(int no);
}

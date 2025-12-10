package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.RecipeVO;

public interface RecipeService {
	public List<RecipeVO> recipeListData(Map map);
	public int recipeTotalPage();
	public List<RecipeVO> recipeTop10();
}

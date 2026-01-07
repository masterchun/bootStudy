package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.FoodMapper;
import com.sist.web.vo.FoodVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
	private final FoodMapper mapper;
	
	@Override
	public List<FoodVO> foodListData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		mapper.foodListData(map);
		return (List<FoodVO>)map.get("pResult");
	}

	@Override
	public int foodTotalPage() {
		// TODO Auto-generated method stub
		return mapper.foodTotalPage();
	}

	@Override
	public FoodVO foodDetailData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		mapper.foodHitIncrement((int)map.get("pNo"));
		mapper.foodDetailData(map);
		return ((List<FoodVO>)map.get("pResult")).get(0);
	}

	@Override
	public List<FoodVO> foodFindData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.foodFindData(map);
	}

	@Override
	public int foodFindTotalData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.foodFindTotalData(map);
	}

}

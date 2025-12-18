package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.CampingMapper;
import com.sist.web.vo.CampingVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampingServiceImpl implements CampingService {
	private final CampingMapper mapper;

	@Override
	public List<CampingVO> campingListData(int start) {
		// TODO Auto-generated method stub
		return mapper.campingListData(start);
	}

	@Override
	public int campingTotalPage() {
		// TODO Auto-generated method stub
		return mapper.campingTotalPage();
	}
	
}

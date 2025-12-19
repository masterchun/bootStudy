package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.CampingMapper;
import com.sist.web.vo.CampingVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {
	private final CampingMapper cMapper;

	@Override
	public List<CampingVO> campingMainData() {
		// TODO Auto-generated method stub
		return cMapper.campingMainData();
	}

}

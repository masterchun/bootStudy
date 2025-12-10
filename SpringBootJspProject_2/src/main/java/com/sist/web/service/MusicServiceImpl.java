package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.mapper.MusicMapper;
import com.sist.web.vo.MusicVO;

@Service
public class MusicServiceImpl implements MusicService{
	@Autowired
	private MusicMapper mapper;

	@Override
	public List<MusicVO> musicListData(Map map) {
		// TODO Auto-generated method stub
		return mapper.musicListData(map);
	}

	@Override
	public int musicTotalPage() {
		// TODO Auto-generated method stub
		return mapper.musicTotalPage();
	}
	
	
}

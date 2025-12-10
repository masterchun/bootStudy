package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.MusicVO;

@Mapper
@Repository
public interface MusicMapper {
	public List<MusicVO> musicListData(Map map);
	
	public int musicTotalPage();
}

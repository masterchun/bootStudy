package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.TheaterVO;

@Mapper
@Repository
public interface TheaterMapper {
	@Select("SELECT * FROM theater")
	public List<TheaterVO> theaterListData();
}

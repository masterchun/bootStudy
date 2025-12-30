package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.SeoulVO;

@Mapper
@Repository
public interface SeoulMapper {
	public List<SeoulVO> seoulListData(Map map);
	public int seoulTotalPage(Map map);
}
// 인터페이스는 서로 다른 클래스 연결 (리모콘) => 모든 변수 / 모든 메소드는 public
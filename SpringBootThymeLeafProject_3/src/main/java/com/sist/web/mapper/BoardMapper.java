package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.BoardVO;

@Mapper
@Repository
public interface BoardMapper {
	public List<BoardVO> boardListData(Map map);
	public int boardRowCount();
	public void boardInsert(BoardVO vo);
	public void boardHitIncrement(int no);
	public BoardVO boardDetailData(int no);
	public String boardGetPassword(int no);
	public void boardUpdate(BoardVO vo);
	public BoardVO boardParentInfoData(int no);
	public void boardGroupStepIncrement(BoardVO vo);
	public void boardReplyInsert(BoardVO vo);
	public void boardDepthIncrement(int no);
	public BoardVO boardDeleteInfoData(int no);
	public void boardSubjectUpdate(int no);
	public void boardDelete(int no);
	public void boardDepthDecrement(int no);
} 

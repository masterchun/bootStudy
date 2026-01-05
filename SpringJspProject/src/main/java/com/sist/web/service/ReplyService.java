package com.sist.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sist.web.vo.ReplyVO;

public interface ReplyService {
	public List<ReplyVO> replyListData(@Param("cno") int cno, @Param("type") int type);
	public void replyInsert(ReplyVO vo);
	public void replyUpdate(ReplyVO vo);
	public void replyDelete(int no);
}

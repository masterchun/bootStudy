package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.web.mapper.BoardMapper;
import com.sist.web.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper mapper;

	@Override
	public List<BoardVO> boardListData(Map map) {
		// TODO Auto-generated method stub
		return mapper.boardListData(map);
	}

	@Override
	public int boardRowCount() {
		// TODO Auto-generated method stub
		return mapper.boardRowCount();
	}

	@Override
	public void boardInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		mapper.boardInsert(vo);
	}

	@Override
	public BoardVO boardDetailData(int no) {
		// TODO Auto-generated method stub
		mapper.boardHitIncrement(no);
		return mapper.boardDetailData(no);
	}

	@Override
	public BoardVO boardUpdateData(int no) {
		// TODO Auto-generated method stub
		return mapper.boardDetailData(no);
	}

	@Override
	public boolean boardUpdate(BoardVO vo) {
		// TODO Auto-generated method stub
		boolean bCheck = false;
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck = true;
			mapper.boardUpdate(vo);
		}
		
		return bCheck;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void boardReplyInsert(int pno, BoardVO vo) {
		// TODO Auto-generated method stub
		BoardVO pvo = mapper.boardParentInfoData(pno);
		mapper.boardGroupStepIncrement(pvo);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step() + 1);
		vo.setGroup_tab(pvo.getGroup_tab() + 1);
		mapper.boardReplyInsert(vo);
		mapper.boardDepthIncrement(pno);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean boardDelete(int no, String pwd) {
		// TODO Auto-generated method stub
		boolean bCheck = false;
		String db_pwd = mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			bCheck = true;
			BoardVO vo = mapper.boardDeleteInfoData(no);
			if(vo.getDepth() == 0) {
				mapper.boardDelete(no);
			} else {
				mapper.boardSubjectUpdate(no);
			}
			mapper.boardDepthDecrement(vo.getRoot());
		}
		
		return bCheck;
	}
	
}

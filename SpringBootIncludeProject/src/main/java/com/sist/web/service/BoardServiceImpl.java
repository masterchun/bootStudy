package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.BoardMapper;
import com.sist.web.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardMapper mapper;

	@Override
	public List<BoardVO> boardListData(int start) {
		// TODO Auto-generated method stub
		return mapper.boardListData(start);
	}

	@Override
	public int boardTotalPage() {
		// TODO Auto-generated method stub
		return mapper.boardTotalPage();
	}

	@Override
	public BoardVO boardDetail(int no) {
		// TODO Auto-generated method stub
		mapper.hitIncrement(no);
		return mapper.boardDetail(no);
	}

	@Override
	public void boardInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		mapper.boardInsert(vo);
	}

	@Override
	public BoardVO boardUpdateData(int no) {
		// TODO Auto-generated method stub
		return mapper.boardDetail(no);
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
	public boolean boardDelete(int no, String pwd) {
		// TODO Auto-generated method stub
		boolean bCheck = false;
		String db_pwd = mapper.boardGetPassword(no);
		
		if(db_pwd.equals(pwd)) {
			bCheck = true;
			mapper.boardDelete(no);
		}
		
		return bCheck;
	}
	
}

package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.BoardVO;

@Mapper
@Repository
public interface BoardMapper {
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit "
		  + "FROM springboard "
		  + "ORDER BY no DESC "
		  + "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	public List<BoardVO> boardListData(int start);
	
	@Select("SELECT CEIL(COUNT(*)/ 10.0) FROM springboard")
	public int boardTotalPage();
	
	@Update("UPDATE springBoard SET hit = hit + 1 WHERE no = #{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no, name, subject, content, hit, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday "
		  + "FROM springBoard "
		  + "WHERE no = #{no}")
	public BoardVO boardDetail(int no);
	
	@Insert("INSERT INTO springBoard VALUES(sb_no_seq.nextval, #{name}, #{subject}, #{content}, #{pwd}, SYSDATE, 0)")
	public void boardInsert(BoardVO vo);
	
	@Select("SELECT pwd FROM springBoard WHERE no = #{no}")
	public String boardGetPassword(int no);
	
	@Update("UPDATE springBoard SET "
		  + "name = #{name}, subject = #{subject}, content = #{content} "
		  + "WHERE no = #{no}")
	public void boardUpdate(BoardVO vo);
	
	@Delete("DELETE FROM springBoard WHERE no = #{no}")
	public void boardDelete(int no);
}

package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.BoardVO;

@Mapper
@Repository
public interface BoardMapper {
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'yyyy-mm-dd') as dbday, hit "
		  + "FROM board_3 "
		  + "ORDER BY no DESC "
		  + "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	public List<BoardVO> boardListData(int start);
	
	@Select("SELECT CEIL(COUNT(*) / 10.0) FROM board_3")
	public int boardTotalPage();
	
	@SelectKey(keyProperty = "no", resultType = int.class, before = true, statement = "SELECT NVL(MAX(no) + 1, 1) as no FROM board_3")
	@Insert("INSERT INTO board_3 VALUES(#{no}, #{name}, #{subject}, #{content}, #{pwd}, SYSDATE, 0)")
	public void boardInsert(BoardVO vo);
	
	@Update("UPDATE board_3 SET "
		  + "hit = hit + 1 "
		  + "WHERE no = #{no}")
	public void boardHitIncrement(int no);
	
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate, 'yyyy-mm-dd') as dbday, hit "
		  + "FROM board_3 "
		  + "WHERE no = #{no}")
	public BoardVO boardDetailData(int no);
	
	@Select("SELECT pwd FROM board_3 WHERE no = #{no}")
	public String boardGetPassword(int no);
	
	@Delete("DELETE FROM board_3 WHERE no = #{no}")
	public void boardDelete(int no);
	
	@Update("UPDATE board_3 SET "
		  + "name = #{name}, subject = #{subject}, content = #{content} "
		  + "WHERE no = #{no}")
	public void boardUpdate(BoardVO vo);
}

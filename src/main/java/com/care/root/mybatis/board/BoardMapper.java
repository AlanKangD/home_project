package com.care.root.mybatis.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;

public interface BoardMapper {
	public List<BoardDTO> boardAllList(@Param("s") int start, @Param("e") int end); //게사판 전체 보기 기능(전체 데이터)
	//데이터 베이스로 값을 보낼때 매개변수의 값이 두개 이상일때는 꼭 param이라는 것을 써줘야합니다.
	public int writeSave(BoardDTO dto); //게시판 정보 저장
	public BoardDTO contentView(int writeNo); //게시물 상세보기 기능(한명의 데이터)
	public void upHit(int writeNo); //조회 수 증가 기능
	public int modify(BoardDTO dto); //게시물 수정 기능
	public int delete(int writeNo); //게시물 삭제 기능
	public void addReply(BoardRepDTO dto); //답글 저장
	public List<BoardRepDTO> getRepList(int write_group); //답글 보기 기능
	public int selectBoardCount();
}
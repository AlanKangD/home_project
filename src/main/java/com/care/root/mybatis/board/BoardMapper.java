package com.care.root.mybatis.board;

import java.util.List;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;

public interface BoardMapper {
	public List<BoardDTO> boardAllList(); //게사판 전체 보기 기능(전체 데이터)
	public int writeSave(BoardDTO dto); //게시판 정보 저장
	public BoardDTO contentView(int writeNo); //게시물 상세보기 기능(한명의 데이터)
	public void upHit(int writeNo); //조회 수 증가 기능
	public int modify(BoardDTO dto); //게시물 수정 기능
	public int delete(int writeNo); //게시물 삭제 기능
	public void addReply(BoardRepDTO dto); //답글 저장
}
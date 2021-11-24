package com.care.root.mybatis.board;

import java.util.List;

import com.care.root.board.dto.BoardDTO;

public interface BoardMapper {
	public List<BoardDTO> boardAllList(); //게사판 전체 보기 기능
	public int writeSave(BoardDTO dto); //게시판 정보 저장
}

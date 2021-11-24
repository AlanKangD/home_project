package com.care.root.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardService {
	public void boardAllList(Model model); //게시판 전체 조회
	public String writeSave(MultipartHttpServletRequest mul, 
	         HttpServletRequest request); // 게시판 정보 저장
}

package com.care.root.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface BoardFileService {
	public static final String IMAGE_REPO = "C:/Users/samsung/Desktop/Spring/image_test";
			//물리적 저장소 위치
	public String getMessage(HttpServletRequest request,
								String msg, String url); //저장 완료 메시지 전달 용도
	public String saveFile(MultipartFile file); //file저장 하는 메소드
}

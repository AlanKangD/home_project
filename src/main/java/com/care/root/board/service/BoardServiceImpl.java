package com.care.root.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.mybatis.board.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired BoardMapper mapper;
	@Autowired BoardFileService bfs;
	
	@Override
	public void boardAllList(Model model) {
		model.addAttribute("boardList", mapper.boardAllList());
		
	}
	
	public String writeSave(MultipartHttpServletRequest mul, 
	         HttpServletRequest request) {
	      BoardDTO dto = new BoardDTO();
	      dto.setTitle( mul.getParameter("title") );
	      dto.setContent( mul.getParameter("content") );
	      dto.setId(mul.getParameter("id"));

	      MultipartFile file = mul.getFile("image_file_name");
	      
	      if(file.getSize() != 0) {
	         //이미지 있을경우 처리
	    	  dto.setImageFileName(bfs.saveFile(file));
	      }else {
	         dto.setImageFileName("nan");
	      }
	      int result = 0;
	      String msg, url;
	      result = mapper.writeSave(dto);
	      if(result == 1) {
	         msg = "새글이 추가되었습니다";
	         url = "/board/boardAllList";
	      }else {
	    	  msg = "문제가 발생되었습니다!!";
		      url = "/board/wrtieForm";
	      }
	      return bfs.getMessage(request, msg, url);
	   }

}

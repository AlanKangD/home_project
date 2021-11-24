package com.care.root.board.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardFileServiceImpl implements BoardFileService{

	@Override
	public String getMessage(HttpServletRequest request, String msg, String url) {
		String massage = null;
		String path = request.getContextPath();
		massage = "<script>alert('"+ msg +"');";
		massage += "location.href='"+path+url+"'; </script>";
		
		return massage;
	}
	
	public String saveFile(MultipartFile file) {
	    SimpleDateFormat simpl = new SimpleDateFormat("yyyyMMddHHmmss-");
	    Calendar calendar = Calendar.getInstance();
	    String sysFileName = 
	   simpl.format(calendar.getTime()) + file.getOriginalFilename();
	    File saveFile = new File(IMAGE_REPO+"/"+sysFileName);
	    try {
	   file.transferTo(saveFile);//해당 위치에 파일 저장
	    }catch (Exception e) {
	   e.printStackTrace();
	    }
	    return sysFileName;
	}

}

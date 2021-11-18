package com.care.root.member.service;

import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;

public interface MemberService {
	public int userCheck(String id, String pw); //회원 체크 기능
	public void memberInfo(Model model); //회원 전체보기 기능
	public void getMember(String id, Model model); //하나의 회원정보 가져오기 기능
	public int register(MemberDTO dto); //회원가입기능
	public void update(String id, String pw, String addr); //회원수정
	public void keepLogin(String sessionId, java.sql.Date limitDate,String id); //자동 로그인 쿠키
	public MemberDTO getUserSession(String sessionId); //인터셉터에서 사용하는 sessionId가져오는 코드
} 

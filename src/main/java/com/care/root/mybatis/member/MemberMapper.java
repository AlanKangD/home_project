package com.care.root.mybatis.member;

import java.util.ArrayList;
import java.util.Map;

import com.care.root.member.dto.MemberDTO;

public interface MemberMapper {
	public MemberDTO getMember(String id); //id를 키워드로 하나의 값만 얻어옴
	public ArrayList<MemberDTO> memberInfo();  //전체의 데이터베이스를 얻어옴
	public int register(MemberDTO dto); //회원가입기능 성공을 하면 1 실패를 하면 0
	public void update(String id, String pw, String addr); //회원 수정 기능
	public void keepLogin(Map<String, Object> map); //자동 로그인 쿠키 처리
	public MemberDTO getUserSession(String sessionId); //인터셉터에서 사용하는 sessionId가져오는 코드
}

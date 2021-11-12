package com.care.root.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired MemberMapper mapper;

	@Override
	public int userCheck(String id, String pw) {
			MemberDTO dto = mapper.getMember(id);
			if(dto != null) {
				if(pw.equals(dto.getPw())) {
					return 0;
				}
			}
			
		return 1;
		//1일때 아이디 없음 0일때 아이디 있음
	}

}

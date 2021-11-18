package com.care.root.member.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired MemberMapper mapper;
	BCryptPasswordEncoder encoder;
	public MemberServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}
	

	@Override
	public int userCheck(String id, String pw) {
			MemberDTO dto = mapper.getMember(id);
			if(dto != null) {
				//if(pw.equals(dto.getPw())) {
				if(encoder.matches(pw, dto.getPw())) {
					return 0;
				}
			}
			
		return 1;
		//1일때 아이디 없음 0일때 아이디 있음
	}

	@Override
	public void memberInfo(Model model) {
		model.addAttribute("memberList", mapper.memberInfo());
		
	}

	@Override
	public void getMember(String id, Model model) {
		model.addAttribute("info",mapper.getMember(id));
		//여기에서는 id의 값으로 정보를 찾아오는 것인데 중복되는 sql명령문이 
		//userCheck에서 사용되었기 때문에 그 명령문을 사용하면됩니다.
	}

	@Override
	public int register(MemberDTO dto) {
		System.out.println("변경 전 : " + dto.getPw());
		String securePw = encoder.encode(dto.getPw());
		System.out.println("변경 후 : " + securePw);
		// 위에는 디버깅
		
		dto.setPw(securePw);
		int result = 0;
		try {
			result = mapper.register(dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public void update(String id, String pw, String addr) {
		System.out.println("id" + id);
		System.out.println("pw" + pw);
		System.out.println("add" + addr);
		mapper.update(id, pw, addr);
	}


	@Override
	public void keepLogin(String sessionId, Date limitDate, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", sessionId);
		map.put("limitDate", limitDate);
		map.put("id", id);
		mapper.keepLogin(map);
		//map으로도 데이터베이스를 처리할 수 있습니다.
	}


	@Override
	public MemberDTO getUserSession(String sessionId) {
		return mapper.getUserSession(sessionId);
		
	}

}

package com.care.root.member.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;
import com.care.root.common.session.MemberSessionName;
import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter
							implements MemberSessionName{

	@Autowired MemberService ms; //서비스를 사용해야 하기때문에 생성해준다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("auto login execute");
		/*
		for(Cookie c : request.getCookies()) {
			if(c.getName().equals("loginCookie")) {
				
			}
		}
		
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		
		두개의 코드 같은의미의 코드입니다. 인터셉터에서는 하단의 코드를 사용합니다.
		*/
		
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		if(loginCookie != null) {
			MemberDTO dto = ms.getUserSession(loginCookie.getValue());
			if(dto != null) {
//				HttpSession session = request.getSession();
//				session.setAttribute(LOGIN, dto.getId()); 주석의 코드는 하단의 코드와 같은 의미 입니다.
				request.getSession().setAttribute(LOGIN, dto.getId());
				
			}
		}
		
		return true;
	}
	
}

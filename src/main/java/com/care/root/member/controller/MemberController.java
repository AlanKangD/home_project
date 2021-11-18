package com.care.root.member.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.root.common.session.MemberSessionName;
import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;

import oracle.jdbc.proxy.annotation.GetProxy;

@Controller
@RequestMapping("member") //공통적으로 mapping안에 값이 들어가서 위에서 공동 단어를 적어주면 하단에 따로 적지 않아도 됩니다.
public class MemberController implements MemberSessionName{
	@Autowired MemberService ms;
	
	@GetMapping("/login")
	public String login() {
		
		return "member/login";
	}
	
	@PostMapping("/user_check")
	public String userCheck(@RequestParam String id,
								@RequestParam String pw,
								@RequestParam(required = false) String autoLogin,
									RedirectAttributes rs) {
		// RedirectAttributes 값을 넘겨줄때 Model이 아닌 다른 방법
		System.out.println("autoLogin : " + autoLogin);
		int result = ms.userCheck(id, pw);
		if(result == 0) {
			rs.addAttribute("id", id); // 아이디가 성공하면 값을 넘겨줄때 쓰임
			rs.addAttribute("autoLogin", autoLogin);
			return "redirect:successLogin";
		}
		return "redirect:login";
		
	}
	
	@GetMapping("/successLogin")
	public String successLogin(@RequestParam String id,
								HttpSession session,
								@RequestParam(required = false) String autoLogin,
									HttpServletResponse response) {
		//@RequestParam 은 userCheck에서 RedirectAttributes 값을 넘겨 받은 것입니다. 
		if(autoLogin != null) {
			int limitTime = 60*60*24*90; //90일
			Cookie loginCookie = new Cookie("loginCookie", session.getId()); //session.getId()은 유일한 값
			loginCookie.setMaxAge(limitTime);
			loginCookie.setPath("/"); //내가 쿠키를 만들었는데 쿠키가 어디까지 전달해준다는 의미입니다.
			response.addCookie(loginCookie);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date()); //자바에서 처리해주는 DATE를 설정해줍니다.(현재시간을 얻어옵니다.)
			cal.add(Calendar.MONTH, 3); //month는 3개월 후로 설정해주세요 라는 뜻입니다.
			
			java.sql.Date limitDate = new java.sql.Date(cal.getTimeInMillis());
				ms.keepLogin(session.getId(), limitDate, id);
		}
		session.setAttribute(LOGIN, id);
		return "member/successLogin";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session,
							HttpServletResponse response,
							@CookieValue(value="loginCookie", required = false) Cookie loginCookie) {
		
		if(session.getAttribute(LOGIN) != null) {
			if(loginCookie != null) {
				loginCookie.setMaxAge(0);
				loginCookie.setPath("/");
				response.addCookie(loginCookie);
				//이렇게 만약 logout을 눌럿을때 cookie의 값이 있다면 시간을 0으로 만들어주고 
				//사용자 쿠키를 없애주겠다라는 의미지만 로그아웃이 바로 진행이 안되고 path까지 진행해주어야 
				//최상위부터 하위까지 모든 쿠키를 삭제해줄수 있습니다. 
				ms.keepLogin("nan", new java.sql.Date(System.currentTimeMillis()), (String)session.getAttribute(LOGIN));
				
			}
			session.invalidate();
		}
		return "redirect:/index"; //그래서 절대경로를 적어줍니다.
//		return "redirect:index"; 리다이렉트를 할 경우에 이렇게 적으면 root/member/index라고 마지막경로만 바꿔주기 때문에 404에러가 뜹니다.
	}
	
	@GetMapping("memberInfo")
	public String memberInfo(Model model, HttpSession session) {
		//if(session.getAttribute(LOGIN) != null) {
			ms.memberInfo(model);
			return "member/memberInfo";
			//}
		//return "redirect:login";
	}
	@GetMapping("info")
	public String info(@RequestParam String id, Model model) {
		ms.getMember(id,model);
		
		return "member/info";
	}
	@GetMapping("register_form")
	public String registerForm() {
		
		return "member/register";
	}
	@PostMapping("register")
	public String register(MemberDTO dto) {
		int result = ms.register(dto);
		if(result == 1) 
			return "redirect:login";
		
		return "redirect:register_form"; 
	}
	@GetMapping("update_form")
	public String update_form(@RequestParam String id,
								Model model) {
			model.addAttribute("id", id);
		return "member/update_form";
	}
	@PostMapping("update")
	public String update( @RequestParam("id") String id,
							@RequestParam("pw") String pw,
							 @RequestParam("addr") String addr) {
		ms.update(id, pw, addr);
		
		return "redirect:memberInfo";
	}
	
	
}

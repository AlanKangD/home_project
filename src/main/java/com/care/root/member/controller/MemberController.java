package com.care.root.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.root.member.service.MemberService;

@Controller
@RequestMapping("member") //공통적으로 mapping안에 값이 들어가서 위에서 공동 단어를 적어주면 하단에 따로 적지 않아도 됩니다.
public class MemberController {
	@Autowired MemberService ms;
	
	@GetMapping("/login")
	public String login() {
		
		return "member/login";
	}
	
	@PostMapping("/user_check")
	public String userCheck(@RequestParam String id,
								@RequestParam String pw,
									RedirectAttributes rs) {
		// RedirectAttributes 값을 넘겨줄때 Model이 아닌 다른 방법
		
		int result = ms.userCheck(id, pw);
		if(result == 0) {
			rs.addAttribute("id", id); // 아이디가 성공하면 값을 넘겨줄때 쓰임
			return "redirect:successLogin";
		}
		return "redirect:login";
		
	}
	
	@GetMapping("/successLogin")
	public String successLogin(@RequestParam String id,
								HttpSession session) {
		//@RequestParam 은 userCheck에서 RedirectAttributes 값을 넘겨 받은 것입니다. 
		session.setAttribute("loginUser", id);
		return "member/successLogin";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		if(session.getAttribute("loginUser") != null) {
			session.invalidate();
		}
		return "redirect:/index"; //그래서 절대경로를 적어줍니다.
//		return "redirect:index"; 리다이렉트를 할 경우에 이렇게 적으면 root/member/index라고 마지막경로만 바꿔주기 때문에 404에러가 뜹니다.
	}
	
}

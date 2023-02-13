package goodee.gdj58.online.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;

@Slf4j	
@Controller
public class MainController {
	
	// 인덱스
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	// 비밀번호 변경 form
	@GetMapping("/{path:^student$|^teacher$|^employee$}/modifyPw")
	public String modifyPw(@PathVariable String path) {
		log.debug("\u001B[31m"+path+"<--modifyPw path");
		return "modifyPw";
	}
}

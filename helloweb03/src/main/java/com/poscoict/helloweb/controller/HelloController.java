package com.poscoict.helloweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@RequestMapping("/hello") // 요청이 들어오면 밑의 메서드 실행한다는 뜻
	public String hello() {
		return "/WEB-INF/views/hello.jsp"; // forward 같은 너낌?
	}
}

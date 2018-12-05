package com.demo2.springboot2.c15session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

	@GetMapping("/session/redis")
	public String getSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		System.out.println(session.getClass());
		System.out.println(session.getId());
		String name = "zsl";
		System.out.println(session.getAttribute("user"));
		session.setAttribute("user", name);
		return "hey:" + name;
	}

}

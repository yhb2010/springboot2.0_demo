package com.demo.springboot2.c5database.beetl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.demo.springboot2.c5database.beetl.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/credit/{id}")
	public Integer getCredit(@PathVariable int id){
		return userService.getCredit(id);
	}

}
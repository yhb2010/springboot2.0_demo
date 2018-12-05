package com.beanPostProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	private MyService myService;

	@GetMapping("/async")
	public String async() {
		myService.a();
		return "success";
	}

}

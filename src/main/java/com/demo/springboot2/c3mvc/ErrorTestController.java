package com.demo.springboot2.c3mvc;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorTestController {

	@PostMapping("/error/demo")
	public String error(@RequestBody Map map) {
		String s = null;
		s.indexOf("c");
		return "aa";
	}

}

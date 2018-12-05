package com.demo.springboot2.c3mvc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot2.domain.User;

@RestController
public class JacksonController {

	@GetMapping("/now.json")
	public Map now(){
		Map map = new HashMap<>();
		map.put("time", new Date());
		map.put("user", new User(1, "zsl"));
		return map;
	}

}

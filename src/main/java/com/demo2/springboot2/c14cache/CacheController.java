package com.demo2.springboot2.c14cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo2.springboot2.c14cache.domain.Menu;

@RestController
public class CacheController {

	@Autowired
	private CacheServiceInterface cacheServiceInterface;

	@GetMapping("/cache/simple")
	public String getMenu(){
		return cacheServiceInterface.getMenu();
	}

	@GetMapping("/cache/simple2")
	public String getMenu2(){
		return cacheServiceInterface.getMenu2(3214);
	}

	@GetMapping("/cache/simple3")
	public String getMenu3(){
		return cacheServiceInterface.getMenu3(new Menu(1));
	}

}

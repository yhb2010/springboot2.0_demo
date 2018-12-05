package com.demo2.springboot2.c12redis;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo2.springboot2.c12redis.domain.RedisUser;

@RestController
public class RedisController4 {

	@Autowired
	//使用jsonRedisTemplate bean
	private RedisTemplate<Object, Object> jsonRedisTemplate;

	@GetMapping("/redis4/template/set")
	private void set(){
		jsonRedisTemplate.opsForValue().set("user3", new RedisUser());
	}

}

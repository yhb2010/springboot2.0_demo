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
public class RedisController3 {

	@Autowired
	//使用strKeyRedisTemplate bean
	private RedisTemplate<Object, Object> strKeyRedisTemplate;

	@GetMapping("/redis3/template/set")
	private void set(){
		strKeyRedisTemplate.opsForValue().set("user2", new RedisUser());
	}

}

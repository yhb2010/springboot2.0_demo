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
public class RedisController2 {

	@Autowired
	private RedisTemplate redisTemplate;

	@GetMapping("/redis/template/set")
	//存储的对象必须是可序列化的，key是类似\xAC\xED\x00\x05t\x00\x05user1，前面是对象的描述信息
	//redisTemplate默认的序列化策略是JdkSerializationRedisSerializer
	private void set(){
		redisTemplate.opsForValue().set("user", new RedisUser());
	}

}

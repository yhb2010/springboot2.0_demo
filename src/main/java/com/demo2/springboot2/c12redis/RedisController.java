package com.demo2.springboot2.c12redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

	@Autowired
	//springboot内置的redis操作接口，适合key和value都是字符串的情况，包括list和hash中的元素
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate redisTemplate;

	@GetMapping("/redis/set/{str}")
	private String set(@PathVariable String str){
		stringRedisTemplate.opsForValue().set("testenv", str);
		//opsForXXX提供了opsForValue、opsForList、opsForHash
		return stringRedisTemplate.opsForValue().get("testenv");
	}

	@GetMapping("/redis/set2/{str}")
	private String set2(@PathVariable String str){
		//redisTemplate提供了boundValueOps来指定一个key，这样后面的操作就不需要指定key了
		//boundValueOps、boundListOps、boundHashOps、boundSetOps、boundZSetOps
		BoundValueOperations<String, String> opt = redisTemplate.boundValueOps("testenv2");
		opt.set("a");
		opt.set("b");
		return opt.get();
	}

	@GetMapping("/redis/set3/{str}")
	private String set3(@PathVariable String str){
		redisTemplate.execute(new RedisCallback<String>() {
			@Override
			//RedisConnection提供了低级别操作，用byte数组作为参数操作redis服务器
			public String doInRedis(RedisConnection arg0) throws DataAccessException {
				arg0.set("testenv3".getBytes(), str.getBytes());
				return new String(arg0.get("testenv3".getBytes()));
			}
		});
		return "sucess";
	}

	@GetMapping("/redis/pub/{str}")
	private void set4(@PathVariable String str){
		//向channel发送消息，第一个参数是channel名称，第二个参数是消息体。redisTemplate将消息体序列化成字节后，发送到redis server
		redisTemplate.convertAndSend("news.test", "测试");
	}

}

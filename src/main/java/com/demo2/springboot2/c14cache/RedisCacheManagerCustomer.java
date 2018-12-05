package com.demo2.springboot2.c14cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//对于redis缓存，还可以定制缓存项的存活时间，缓存名是否在redis中加上前缀等
//这里的设置和RedisConfig里的cacheManager设置重复了，可以删除
@Configuration
public class RedisCacheManagerCustomer {

	@Bean
	public RedisCacheConfiguration redisCacheConfiguration() {
	    return RedisCacheConfiguration
	            .defaultCacheConfig()
	            .serializeKeysWith(
	                    RedisSerializationContext
	                            .SerializationPair
	                            .fromSerializer(new StringRedisSerializer()))
	            .serializeValuesWith(
	                    RedisSerializationContext
	                            .SerializationPair
	                            .fromSerializer(new GenericJackson2JsonRedisSerializer()));
	}

}

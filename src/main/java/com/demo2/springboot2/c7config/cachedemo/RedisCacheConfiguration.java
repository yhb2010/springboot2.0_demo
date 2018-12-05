package com.demo2.springboot2.c7config.cachedemo;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

//Redis缓存配置需要确保RedisTemplate已经配置，因此使用了注解@AutoConfigureAfter，表示此配置类需要在RedisAutoConfiguration配置类后再生效
//@ConditionalOnBean表示如果成功配置好RedisTemplate，此配置才能继续生效
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@ConditionalOnBean(RedisTemplate.class)
@ConditionalOnMissingBean(CacheManager.class)
@Conditional(CacheCondition.class)
public class RedisCacheConfiguration {

	@Bean
	public RedisCacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate){
		//RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		return null;
	}

}

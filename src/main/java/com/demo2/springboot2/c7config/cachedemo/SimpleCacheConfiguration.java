package com.demo2.springboot2.c7config.cachedemo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

//联合多个条件：
//注解@Configuration向spring表明这是一个配置表
//注解@ConditionalOnMissingBean可以接受一个或多个类，如果还没有配置过CacheManager，则配置类可以生效。
//注解@Conditional是一个更为通用的条件配置类，其后参数的实例实现了Condition接口的match方法
@Configuration
@ConditionalOnMissingBean(CacheManager.class)
@Conditional(CacheCondition.class)
public class SimpleCacheConfiguration {

	@Bean
	public ConcurrentMapCacheManager cacheManager(){
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
		return cacheManager;
	}

}

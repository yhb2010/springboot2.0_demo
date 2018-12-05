package com.demo2.springboot2.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisCacheManager implements CacheManager {

	@Autowired
	private RedisCache redisCache;

	@SuppressWarnings("unchecked")
	@Override
	public <K, V> Cache<K, V> getCache(String s) throws CacheException {
		return redisCache;
	}

}

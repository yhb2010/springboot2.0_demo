package com.demo2.springboot2.c14cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;

/**
 * RedisAndLocalCache 是我们系统的核心，他实现了Cache接口，类，会实现如下操作。
 *
 * get操作，通过Key取对应的缓存项，在调用父类RedisCache之前，会先检测本地缓存是否存在，存在则不需要调用父类的get操作。如果不存在，
 * 调用父类的get操作后，将Redis返回的ValueWrapper放到本地缓存里待下次用。
 * put，调用父类put操作更新Redis缓存，同时广播消息，缓存改变。我们将在下一章讲如何使用Redis的Pub/Subscribe 来同步缓存
 * evict ，同put操作一样，调用父类处理，清空对应的缓存，同时广播消息 putIfAbsent，同put操作一样，调用父类实现，同时广播消息
 *
 * @author DELL
 *
 */
public class RedisAndLocalCache implements Cache {

	// 本地缓存提供
	ConcurrentHashMap<Object, Object> local = new ConcurrentHashMap<Object, Object>();
	RedisCache redisCache;
	TowLevelCacheManager cacheManager;

	public RedisAndLocalCache(TowLevelCacheManager cacheManager, RedisCache redisCache) {
		this.redisCache = redisCache;
		this.cacheManager = cacheManager;
	}

	@Override
	public String getName() {
		return redisCache.getName();
	}

	@Override
	public Object getNativeCache() {
		return redisCache.getNativeCache();
	}

	/* 变量local代表了一个简单的缓存实现， 使用了ConcurrentHashMap。其get方法有如下逻辑实现

通过key从本地取出 ValueWrapper
如果ValueWrapper存在，则直接返回
如果ValueWrapper不存在，则调用父类RedisCache取得缓存项
如果缓存项为空，则说明暂时无此项，直接返回空，等@Cacheable 调用业务方法获取缓存项
	 * @see org.springframework.cache.Cache#get(java.lang.Object)
	 */
	@Override
	public ValueWrapper get(Object key) {
		// 一级缓存先取
		ValueWrapper wrapper = (ValueWrapper) local.get(key);
		if (wrapper != null) {
			return wrapper;
		} else {
			// 二级缓存取
			wrapper = redisCache.get(key);
			if (wrapper != null) {
				local.put(key, wrapper);
			}
			return wrapper;
		}
	}

	/* put方法实现逻辑如下

先调用redisCache，更新二级缓存

调用clearOtherJVM方法，通知其他节点缓存更新

其他节点（包括本节点)的TowLevelCacheManager收到消息后，会调用receiver方法从而实现一级缓存

为了简单起见，一级缓存的同步更新 仅仅是清空一级缓存而并非采用同步更新缓存项。一级缓存将在下一次get方法调用时会再次从Reids里加载最新数据。

一节缓存仅仅简单使用了Map实现，并未实现缓存的多种策略。因此，如果你的一级缓存如果需要各种缓存策略，还需要用一些第三方库或者自行实现，但大部分情况下TowLevelCacheManager都足够使用
	 * @see org.springframework.cache.Cache#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void put(Object key, Object value) {
		System.out.println(value.getClass().getClassLoader());
		redisCache.put(key, value);
		// 通知其他节点缓存更新
		clearOtherJVM();
	}

	@Override
	public void evict(Object key) {
		redisCache.evict(key);
		// 通知其他节点缓存更新
		clearOtherJVM();
	}

	protected void clearOtherJVM() {
		cacheManager.publishMessage(redisCache.getName());
	}

	// 提供给CacheManager清空一节缓存
	public void clearLocal() {
		this.local.clear();
	}

	@Override
	public <T> T get(Object obj, Class<T> class1) {
		return redisCache.get(obj, class1);
	}

	@Override
	public <T> T get(Object obj, Callable<T> callable) {
		return redisCache.get(obj, callable);
	}

	@Override
	public ValueWrapper putIfAbsent(Object obj, Object obj1) {
		return redisCache.putIfAbsent(obj, obj1);
	}

	@Override
	public void clear() {
		redisCache.clear();
	}

}

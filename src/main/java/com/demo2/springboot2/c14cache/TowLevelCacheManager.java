package com.demo2.springboot2.c14cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;

/**实现Redis 俩级缓存
SpringBoot自带的Redis缓存非常容易使用，但由于通过网络访问了Redis，效率还是比传统的跟应用部署在一起的一级缓存略慢。本章中，扩展RedisCacheManager和RedisCache，在访问Redis之前，先访问一个ConcurrentHashMap实现的简单一级缓存，如果有缓存项，则返回给应用，如果没有，再从Redis里取，并将缓存对象放到一级缓存里

当缓存项发生变化的时候，注解@CachePut 和 @CacheEvict会触发RedisCache的put( Object key, Object value)和evict(Object key)操作，俩级缓存需要同时更新ConcurrentHashMap和Redis缓存，且需要通过Redis的Pub发出通知消息，其他Spring Boot应用通过Sub来接收消息，同步更新Spring Boot应用自身的一级缓存。

为了简单起见，一级缓并没有缓存过期策略，用户系统如果会有大量数据需要放到一级缓存，需要再次扩展这里的代码，比如使用LRUHashMap代替Map

 * 首先，创建创建一个新的缓存管理器，命名为TowLevelCacheManager，继承了Spring Boot的RedisCacheManager，重载decorateCache方法。返回的是我们新创建的LocalAndRedisCache 缓存实现。
 * 在Spring Cache中，在缓存管理器创建好每个缓存后，都会调用decorateCache方法，这样缓存管理器子类有机会实现自己的扩展，在这段代码，返回了自定义的RedisAndLocalCache实现。 publishMessage方法提供个给Cache，用于当缓存更新的时候，使用Redis的消息机制通知其他分布式节点的一级别缓存。receiver方法对应于publishMessage方法，当收到消息后，会清空一节缓存。
 * @author DELL
 *
 */
public class TowLevelCacheManager extends RedisCacheManager {

	RedisTemplate redisTemplate;
	@Value("${springext.cache.redis.topic:cache}")
	String topicName;

	public TowLevelCacheManager(RedisTemplate redisTemplate, RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
		super(cacheWriter, defaultCacheConfiguration);
		this.redisTemplate = redisTemplate;
	}

	// 使用RedisAndLocalCache代替Spring Boot自带的RedisCache
	@Override
	protected Cache decorateCache(Cache cache) {
		return new RedisAndLocalCache(this, (RedisCache) cache);
	}

	public void publishMessage(String cacheName) {
		this.redisTemplate.convertAndSend(topicName, cacheName);
	}

	// 接受一个消息清空本地缓存
	public void receiver(String name) {
		RedisAndLocalCache cache = ((RedisAndLocalCache) this.getCache(name));
		if (cache != null) {
			cache.clearLocal();
		}
	}

}

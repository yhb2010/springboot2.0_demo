package com.demo2.springboot2.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCache<K, V> implements Cache<K, V> {

	@Autowired
    private RedisTemplate redisTemplate;
	private final String CACHE_PRE = "springboot2:cache:";

	private byte[] getKey(K k){
		if(k instanceof String){
			return (CACHE_PRE + k).getBytes();
		}
		return SerializationUtils.serialize((Serializable)k);
	}

	@Override
	public void clear() throws CacheException {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(K k) throws CacheException {
		byte[] value = (byte[])redisTemplate.opsForValue().get(getKey(k));
		if(value != null){
			SerializationUtils.deserialize(value);
			System.out.println("从缓存获取结果");
			return SerializationUtils.deserialize(value);
		}
		return null;
	}

	@Override
	public Set keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V put(K k, V v) throws CacheException {
		byte[] key = getKey(k);
		byte[] value = SerializationUtils.serialize((Serializable)v);
		redisTemplate.opsForValue().set(key, value);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V remove(K k) throws CacheException {
		byte[] key = getKey(k);
		byte[] value = redisTemplate.getStringSerializer().serialize(getKey(k));
		redisTemplate.delete(key);
		if(value != null){
			return SerializationUtils.deserialize(value);
		}
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection values() {
		// TODO Auto-generated method stub
		return null;
	}

}

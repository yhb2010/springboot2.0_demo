package com.demo2.springboot2.c14cache;

import java.lang.reflect.Method;
import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <p>redis缓存配置</p>
 * @Configuration  可理解为用spring的时候xml里面的<beans>标签
   @EnableCaching 注解是spring framework中的注解驱动的缓存管理功能。
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public KeyGenerator myKey2() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    CacheManager cacheManager(RedisTemplate redisTemplate) {
        //初始化一个RedisCacheWriter，一个实现redis操作的接口，springboot提供了nolock和lock两种实现，在缓存写操作的时候，前者有较高的性能，后者实现了redis锁
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        //RedisCacheConfiguration用于设置缓存特性，比如缓存ttl、缓存key的前缀，默认ttl是0，不使用前缀。你可以为缓存管理器设置默认的配置，也可以为每一个缓存设置一个配置。
        //最重要的配置是SerializationPair，用于java和redis的序列化和返序列化操作。
        //设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
        		.serializeKeysWith(
        				RedisSerializationContext
        						.SerializationPair
        						.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(
				        RedisSerializationContext
				                .SerializationPair
				                .fromSerializer(new GenericJackson2JsonRedisSerializer()));
        RedisCacheConfiguration configuration = defaultCacheConfig.entryTtl(Duration.ofSeconds(60));
        //使用自己的TowLevelCacheManager替代springboot默认的RedisCacheManager
        TowLevelCacheManager cacheManager = new TowLevelCacheManager(redisTemplate, redisCacheWriter, configuration);
        //RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, configuration);
        return cacheManager;
    }

}
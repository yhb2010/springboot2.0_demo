package com.demo2.springboot2.c14cache;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author DELL
 *
 */
@SpringBootApplication
@EnableCaching
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	//定义一个redis 的频道，默认叫cache，用于pub/sub
	@Value("${springext.cache.redis.topic:cache}")
	String topicName;

	//当缓存发生改变的时候，需要通知分布式系统的TowLevelCacheManager的，清空一级缓存.这里使用Redis实现消息通知，关于Redis消息发布和订阅，参考Redis一章。
	//为了实现Redis的Pub/Sub 模式，我们需要在CacheConfig里添加一些代码，创建一个消息监听器
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new PatternTopic(topicName));
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(final TowLevelCacheManager cacheManager) {
		return new MessageListenerAdapter(new MessageListener() {
			public void onMessage(Message message, byte[] pattern) {
				byte[] bs = message.getChannel();
				try {
					// Sub 一个消息，通知缓存管理器，这里的type就是Cache的名字
					String type = new String(bs, "UTF-8");
					cacheManager.receiver(type);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					// 不可能出错，忽略
				}
			}
		});
	}

}

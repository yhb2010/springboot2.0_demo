package com.demo2.springboot2.c12redis;

import java.text.SimpleDateFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author DELL
 *
 */
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	//主要用来对消息进行序列化，默认采用StringRedisSerializer。
	MessageListenerAdapter listenrAdapter(){
		//MessageListenerAdapter的默认序列化策略是StringRedisSerializer，可以改为JdkSerializationRedisSerializer
		MessageListenerAdapter adapter = new MessageListenerAdapter(new MyRedisChannelListener());
		adapter.setSerializer(new JdkSerializationRedisSerializer());
		return adapter;
	}

	@Bean
	//在redis客户端接收到消息后，通过PatternTopic派发消息到对应的消息监听者，并构造一个线程池任务来调用MessageListener
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenrAdapter){
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenrAdapter, new PatternTopic("news.*"));
		return container;
	}

	//自定义key的序列化策略
	@Bean("strKeyRedisTemplate")
	public RedisTemplate<Object, Object> strKeyRedisTemplate(RedisConnectionFactory connectionFactory){
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setConnectionFactory(connectionFactory);
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		template.setKeySerializer(stringSerializer);
		return template;
	}

	@Bean
	//自定义一个ObjectMapper，用于处理时间
	public ObjectMapper getObjectMapper(){
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		return objectMapper;
	}

	//自定义redis的序列化策略：json形式
	@Bean("jsonRedisTemplate")
	public RedisTemplate<Object, Object> jsonRedisTemplate(RedisConnectionFactory connectionFactory, ObjectMapper mapper){
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer(mapper));
		return template;
	}

}

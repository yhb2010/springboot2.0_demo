package com.demo2.springboot2.c7config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//根据name来读取yml文件配置，根据其值与havingValue是否相等来决定是否生效，如果没有指定havingValue，只要属性不为false，配置都能生效。
//matchIfMissing=true意思是如果没有包含message.center.enabled，配置也能生效。默认为false
@Configuration
@ConditionalOnProperty(name="message.center.enabled", havingValue="true", matchIfMissing=true)
public class ConditionProperties {

	@Bean
	public int getPort(){
		System.out.println("conditionalOnProperty");
		return 1;
	}

}

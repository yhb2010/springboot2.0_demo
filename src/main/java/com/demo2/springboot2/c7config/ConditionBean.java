package com.demo2.springboot2.c7config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo2.springboot2.c7config.service.MyService;
import com.demo2.springboot2.c7config.service.MyService2;
import com.demo2.springboot2.c7config.service.MyService3;

@Configuration
public class ConditionBean {

	@Bean
	public MyService myService(){
		System.out.println("初始化MyService");
		return new MyService();
	}

	@Bean
	//在上下文中存在某个对象时，才会实例化一个bean（注意先后顺序，如果以另一个Configuration的bean为判断，因为顺序问题有可能不起作用）
	//可以在config类上使用@AutoConfigureAfter(EnvConfig.class)保证该config要在EnvConfig完成后在实例化
	@ConditionalOnBean(MyService.class)
	public MyService2 myService2(){
		System.out.println("初始化MyService2");
		return new MyService2();
	}

	@Bean
	//如果没有配置MyService3，则调用myService3
	@ConditionalOnMissingBean
	public MyService3 myService3(){
		System.out.println("初始化MyService3");
		return new MyService3();
	}

}

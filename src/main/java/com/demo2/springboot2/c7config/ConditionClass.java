package com.demo2.springboot2.c7config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo2.springboot2.c7config.service.MyService;
import com.demo2.springboot2.c7config.service.MyService2;

@Configuration
public class ConditionClass {

	@Bean
	//某个类是否在classpath下来决定是否要装载bean
	@ConditionalOnClass(MyService.class)
	//当classpath下没有指定类时，配置生效
	//@ConditionalOnMissingClass(MyService.class)
	public MyService2 myService2(){
		System.out.println("初始化MyService2 class");
		return new MyService2();
	}

}

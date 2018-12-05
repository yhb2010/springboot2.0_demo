package com.beanFactoryPostProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

	@Autowired
    private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public BeanFactoryPostProcessorTest beanFactoryPostProcessorTest(){
		BeanFactoryPostProcessorTest test = new BeanFactoryPostProcessorTest();
		test.setName("张三");
		return test;
	}

	@Bean
	public Object obj(){
		BeanFactoryPostProcessorTest test = (BeanFactoryPostProcessorTest)applicationContext.getBean("beanFactoryPostProcessorTest");
		System.out.println(test);
		return new Object();
	}

}

package com.demo2.springboot2.c7config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class EnvConfig {

	@Autowired
	//Environment可以读取yml，命令行参数、系统属性、操作系统环境变量等，这是最早被初始化的一个类，因此可以用在spring应用的任何地方
	private Environment env;

	@Bean
	public int getServerPort(){
		//程序运行的目录，如果在ide中运行，就是工程目录，user.dir是系统属性
		env.getProperty("user.dir");
		//执行程序的用户的home目录，user.home是系统属性
		env.getProperty("user.home");
		//读取设置的环境变量，不区分大小写
		env.getProperty("JAVA_HOME");
		//读取yml
		System.out.println(env.getProperty("server.port", Integer.class));
		return env.getProperty("server.port", Integer.class);
	}

}

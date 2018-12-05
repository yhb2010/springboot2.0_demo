package com.BeanPostProcessors使用;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 我们项目中经常会涉及AB 测试，这就会遇到同一套接口会存在两种不同实现。实验版本与对照版本需要在运行时同时存在。
 * 此时我们可以在接口的方法上设置具体运行哪个实现。
 */
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}

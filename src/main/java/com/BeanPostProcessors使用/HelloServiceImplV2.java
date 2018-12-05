package com.BeanPostProcessors使用;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImplV2 implements HelloService {
	public String sayHello() {
		return "Hello from V2";
	}

	public String sayHi() {
		return "Hi from V2";
	}
}

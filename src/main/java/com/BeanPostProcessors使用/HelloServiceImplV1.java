package com.BeanPostProcessors使用;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImplV1 implements HelloService {
	public String sayHello() {
		return "Hello from V1";
	}

	public String sayHi() {
		return "Hi from V1";
	}
}

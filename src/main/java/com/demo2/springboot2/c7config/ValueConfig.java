package com.demo2.springboot2.c7config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValueConfig {

	@GetMapping("/config/value")
	//@Value并不能在任何Spring管理的bean中使用，因为它本身是通过AutowiredAnnotationBeanPostProcessor实现的，它是BeanPostProcessor
	//接口的实现类，因此任何BeanPostProcessor和BeanFactoryPostProcessor的子类中都不能使用@Value来注入属性，因为还没有被处理。
	public int getServerPort(@Value("${server.port:8080}") int port){
		return port;
	}

}

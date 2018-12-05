package com.beanPostProcessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cdel.util.anno.clazz.EnableAsync;
import com.cdel.util.anno.clazz.EnableClassInnerProxy;

/**
 * @author DELL
 *调用myService.b()时首先调用的是AOP代理对象而不是目标对象，通过this.b()调用的this指向目标对象，因此调用this.b()将不会执行b事务切面。
	public void a() {
		this.b();
	}

	@Override
	@Async
	public void b() {

	}
	通过this调用不会进入拦截器，需要借助BeanPostProcessor
 */
@EnableClassInnerProxy
@EnableAsync
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}

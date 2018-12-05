package com.BeanPostProcessors使用;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdeledu.domain.ServiceResult;

@RestController
public class HelloController {

	@RoutingInjected
	private HelloService helloService;

	@GetMapping("/v1")
	public ServiceResult<String> sayHello() {
		return ServiceResult.getSuccessResult(helloService.sayHello());
	}

	@GetMapping("/v2")
	public ServiceResult<String> sayHi() {
		return ServiceResult.getSuccessResult(helloService.sayHi());
	}
}

package com.BeanPostProcessors使用;

@RoutingSwitch("V2")
public interface HelloService{

	@RoutingSwitch("V1")
    String sayHello();

	String sayHi();

}
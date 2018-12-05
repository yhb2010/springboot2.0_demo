package com.demo2.springboot2.c10rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo2.springboot2.c10rest.domain.Order;

@RestController
public class RestDemoController {

	@Autowired
	//通过RestTemplateBuilder创建RestTemplate
	private RestTemplateBuilder restTemplateBuilder;

	@GetMapping("/order/{orderId}")
	public Order getOrder(@PathVariable Integer orderId){
		RestTemplate restTemplate = restTemplateBuilder.build();
		return restTemplate.getForObject("http://127.0.0.10:8080/order/{orderId}", Order.class, orderId);
	}

	@GetMapping("/order2/{orderId}")
	public Order getOrder2(@PathVariable Integer orderId){
		RestTemplate restTemplate = restTemplateBuilder.build();
		//直接传入map参数
		Map<String, Object> map = new HashMap<>();
		map.put("orderId", orderId);
		return restTemplate.getForObject("http://127.0.0.10:8080/order/{orderId}", Order.class, map);
	}

	@GetMapping("/order3/{orderId}")
	public Order getOrder3(@PathVariable Integer orderId){
		RestTemplate restTemplate = restTemplateBuilder.build();
		ResponseEntity<Order> result = restTemplate.getForEntity("http://127.0.0.10:8080/order/{orderId}", Order.class, orderId);
		//可以获取http头信息
		HttpHeaders headers = result.getHeaders();
		return result.getBody();
	}

	@GetMapping("/orders/{userId}")
	public List<Order> getOrders(@PathVariable Integer userId){
		RestTemplate restTemplate = restTemplateBuilder.build();
		//typeRef定义是用{}结束的，这里创建了一个ParameterizedTypeReference的子类，依据在类定义中的泛型信息保留的原则，typeRef保留了期望返回的泛型List
		ParameterizedTypeReference<List<Order>> typeRef = new ParameterizedTypeReference<List<Order>>(){};
		Integer offset = 1;
		HttpEntity<Integer> body = new HttpEntity<>(userId);
		//exchange是基础的rest调用接口，需要指明HttpMethod
		ResponseEntity<List<Order>> result = restTemplate.exchange("http://127.0.0.10:8080/order/{orderId}", HttpMethod.GET, body, typeRef, offset);
		return result.getBody();
	}

}

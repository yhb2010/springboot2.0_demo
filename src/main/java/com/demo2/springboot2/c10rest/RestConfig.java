package com.demo2.springboot2.c10rest;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

//定制RestTemplate
@Configuration
public class RestConfig implements RestTemplateCustomizer {

	@Override
	public void customize(RestTemplate restTemplate) {
		SimpleClientHttpRequestFactory jdkHttp = (SimpleClientHttpRequestFactory)restTemplate.getRequestFactory();
		//超时时间1000毫秒，springboot默认使用jdk的URLConnection，还可以使用okHttp，需要okhttp依赖，代码改为：
		jdkHttp.setConnectTimeout(1000);
		//OkHttp3ClientHttpRequestFactory okHttp = (OkHttp3ClientHttpRequestFactory)restTemplate.getRequestFactory();
		//okHttp.setConnectTimeout(1000);
		//okHttp.setWriteTimeout(3000);
		//okHttp.setReadTimeout(5000);
	}

}

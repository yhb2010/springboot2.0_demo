package com.demo2.springboot2.c7config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//使用@ConfigurationProperties获取一组属性，它会自动把-或者_去掉，转化为java命名规范，如context-path转为contextPath
@ConfigurationProperties("server")
@Configuration
public class ConfigProperties {

	private int port;
	private String contextPath;

	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

}

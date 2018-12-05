package com.demo2.springboot2.c15session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author DELL
 *
 */
@SpringBootApplication
@EnableRedisHttpSession
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}

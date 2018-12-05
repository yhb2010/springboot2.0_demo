package com.shopcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cdel.util.anno.clazz.EnableRedisCache;
import com.cdel.util.helper.SpringContextUtil;

@SpringBootApplication
@EnableRedisCache
public class App {

	public static void main(String[] args) {
		SpringContextUtil.setApplicationContext(SpringApplication.run(App.class, args));
	}

}

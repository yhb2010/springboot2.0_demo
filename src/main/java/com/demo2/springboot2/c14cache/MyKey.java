package com.demo2.springboot2.c14cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Service;

import com.demo2.springboot2.c14cache.domain.Menu;

@Service
public class MyKey implements KeyGenerator {

	@Override
	public Object generate(Object arg0, Method arg1, Object... arg2) {
		Menu m = (Menu)arg2[0];
		return m.getId();
	}

}

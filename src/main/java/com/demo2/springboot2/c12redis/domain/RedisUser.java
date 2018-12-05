package com.demo2.springboot2.c12redis.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public @Getter
@Setter
class RedisUser implements Serializable {
	private int id;
	private String name;
}
package com.demo.springboot2.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class User implements Serializable{

	private Integer id;
	private String name;
	private Integer age;

	public User(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}

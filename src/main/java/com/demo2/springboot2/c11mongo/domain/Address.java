package com.demo2.springboot2.c11mongo.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable{

	private Integer id;
	private String area;

}
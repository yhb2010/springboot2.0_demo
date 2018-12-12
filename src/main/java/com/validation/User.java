package com.validation;

import javax.validation.constraints.NotNull;

public class User {

	@NotNull(message = "name不能为空")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

package com.factoryBean;

public class PersonServiceImpl implements PersonService {

	@Override
	public String getNameForDB() {
		System.out.println("get name from db");
		return "zsl";
	}

}

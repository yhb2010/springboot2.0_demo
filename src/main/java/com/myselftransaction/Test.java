package com.myselftransaction;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Test {

	public static final String jdbcDriver = "com.mysql.jdbc.Driver";
	public static final String jdbcUrl = "jdbc:mysql://127.0.0.87:port/dbName?allowMultiQueries=true";
	public static final String jdbcUser = "123";
	public static final String jdbcPassword = "123";

	public static void main(String[] args) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(jdbcDriver);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(jdbcUser);
		dataSource.setPassword(jdbcPassword);

		final UserService service = new UserService(dataSource);

		for(int i=0; i<10; i++){
			new Thread(() -> {service.action();}).start();
		}

		try{
			Thread.sleep(10000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

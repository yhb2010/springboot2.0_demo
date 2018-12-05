package com.demo2.springboot2.c8deploy;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

//@Profile注解可以结合@Configuration和@Component使用，以决定配置类是否生效
//@Profile可以支持多个profile，也可以使用!排除特定profile
@Configuration
public class DataSourceConf {

	@Bean(name = "dataSource")
	@Profile("test")
	//@Profile({"test1", "test2"})
	//@Profile({"test1", "!test2"})
	public DataSource testDataSource(Environment env){
		HikariDataSource test = getDataSource(env);
		test.setMaximumPoolSize(200);
		return test;
	}

	@Bean(name = "dataSource")
	@Profile("prod")
	public DataSource prodDataSource(Environment env){
		HikariDataSource prod = getDataSource(env);
		prod.setMaximumPoolSize(200);
		return prod;
	}

	private HikariDataSource getDataSource(Environment env){
		HikariDataSource ds = new HikariDataSource();
		return ds;
	}

}

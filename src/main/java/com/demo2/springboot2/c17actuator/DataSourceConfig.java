package com.demo2.springboot2.c17actuator;

import javax.sql.DataSource;

import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

	@Bean(name = "dataSource")
	//Environment在springboot中代表环境上下文，包含了application.properties配置的属性，jvm系统属性和操作系统环境变量
	public DataSource datasource(Environment env){
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
		ds.setUsername(env.getProperty("spring.datasource.username"));
		ds.setPassword(env.getProperty("spring.datasource.password"));
		ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		return ds;
	}

	//启动服务后，多了一个监控点：http://127.0.0.10:8083/manage/hikaricp
	@Bean
	//如果还没有声明，则HikariCPEndpoint才会生效
	@ConditionalOnMissingBean
	//生效的另一个条件是系统启用了监控
	@ConditionalOnEnabledEndpoint
	public HikariCPEndpoint hikariCPEndpoint(DataSource ds){
		return new HikariCPEndpoint((HikariDataSource)ds);
	}

}

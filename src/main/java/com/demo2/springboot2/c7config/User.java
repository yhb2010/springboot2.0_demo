package com.demo2.springboot2.c7config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**读取自定义配置文件通过@PropertySource，@PropertySource默认是不支持yml读取的，只支持properties，但是加上app.java里面的properties bean就可以了
 * @author DELL
 *
 */
@Component
@Configuration
@PropertySource({"classpath:test.yml"})
@ConfigurationProperties(prefix = "com.forezp")
@Getter
@Setter
public class User {

	private String name;
	private int age;

}

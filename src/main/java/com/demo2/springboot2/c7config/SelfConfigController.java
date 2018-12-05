package com.demo2.springboot2.c7config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**读取自定义配置文件
 * @author DELL
 *
 */
@RestController
@EnableConfigurationProperties(User.class)
public class SelfConfigController {

	@Autowired
	private User user;

	@GetMapping("/self/config/demo")
	public String name(){
		return user.getName();
	}

}

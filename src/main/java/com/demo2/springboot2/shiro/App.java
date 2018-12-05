package com.demo2.springboot2.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** shiro.ini文件可以删除，本身和shiro无关，是因为没有这个文件其它app启动报错
 * @author DELL
 *
 */
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}

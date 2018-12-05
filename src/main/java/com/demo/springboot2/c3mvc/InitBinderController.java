package com.demo.springboot2.c3mvc;

import java.util.Date;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitBinderController {

	/**spring框架通过WebDataBinder类实现参数绑定，可以在Controller中用注解@InitBinder声明一个方法，来自己扩展绑定的特性
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
	}

	@GetMapping("/date/{date}")
	public String getDate(@PathVariable("date") Date date){
		System.out.println(date);
		return "success";
	}

}

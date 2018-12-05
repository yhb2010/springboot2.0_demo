package com.demo.springboot2.c3mvc;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot2.domain.User;

@RestController
public class ModelAttributeController {

	/**注解ModelAttribute通常在controller的方法上，此方法会首先被调用，并将方法结果作为model的属性，然后再调用对应的controller处理方法
	 * @param id
	 * @param model
	 */
	@ModelAttribute
	public void findUserById(@PathVariable("id") Long id, Model model){
		model.addAttribute("user", new User());
	}
	//如果就返回一个对象，也可以写成
	//@ModelAttribute
	//public User findUserById(@PathVariable("id") Long id){
		//return new User();
	//}

	/**http://127.0.0.10:8083/123/get.json
	 * 对于这个请求，会先调用findUserById方法取得user，放到模型里。使用@ModelAttribute通常可以用来向一个Controller中需要的公共模型添加数据
	 * @param model
	 * @return
	 */
	@GetMapping("/{id}/get.json")
	public String getUser(Model model){
		System.out.println(model.containsAttribute("user"));
		return "success";
	}

}

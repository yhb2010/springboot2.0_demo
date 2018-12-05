package com.demo.springboot2.c3mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.springboot2.domain.User;

@Controller
public class FreemarkerController {

	//http://127.0.0.10:8083/show.html?id=1
	@GetMapping("/show.html")
	public ModelAndView show(Integer id){
		ModelAndView view = new ModelAndView();
		User user = new User(id, "zsl");
		view.addObject(user);
		view.setViewName("userInfo");
		return view;
	}

}

package com.demo.springboot2.c3mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ForwardController {

	@GetMapping("/bbs")
	public String index(){
		return "forward:/bbs/module/1-1.html";
	}

	@GetMapping("/bbs/module/{type}-{page}")
	public ModelAndView module(@PathVariable int type, @PathVariable int page){
		return null;
	}

	@GetMapping("/bbs2")
	public String index2(){
		return "redirect:/bbs/module/1-1.html";
		//或者
		//ModelAndView view = new ModelAndView("redirect:/bbs/module/1-1.html");
		//或者
		//RedirectView view = new RedirectView("/bbs/module/1-1.html");
	}

}

package com.demo.springboot2.c3mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**是用来全局定制化springboot的mvc特性，开发者通过实现WebMvcConfigurer接口来配置应用的mvc全局特性
 * @author DELL
 *
 */
@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

	//拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		//增加一个拦截器，检查会话，url以admin开头的都使用此拦截器
		registry.addInterceptor(new SessionHandlerInterceptor()).addPathPatterns("/admin/**");
	}

	//跨域访问
	@Override
	public void addCorsMappings(CorsRegistry registry){
		//允许所有跨域访问
		//registry.addMapping("/**");
		//或者更为精细的：仅允许来自domain2.com的跨域访问，并且限定访问路径为/api，方法时post或get请求
		registry.addMapping("/api/**").allowedOrigins("http://domain2.com").allowedMethods("POST", "GET");
	}

	//格式化
	@Override
	public void addFormatters(FormatterRegistry registry){
		//对于日期类型参数，spring并没有设置默认的将字符串转换为日期类型，为了支持转换日期类型，需要添加一个DateFormatter类，它实现将字符串转为日期类型：java.util.Date
		registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
	}

	//url到视图的映射
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		//对于index.html的请求，设置返回的视图为index.btl
		registry.addViewController("/index.html").setViewName("/index.btl");
		//所有以.do结尾的请求重定向到/index.html请求
		registry.addRedirectViewController("/**/*.do", "/index.html");
	}

	//其它更多全局定制接口

}

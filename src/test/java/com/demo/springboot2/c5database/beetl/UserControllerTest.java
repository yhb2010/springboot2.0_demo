package com.demo.springboot2.c5database.beetl;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import javax.servlet.http.Cookie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.demo.springboot2.c5database.beetl.service.UserService;

@RunWith(SpringRunner.class)
//spring boot可以单独测试Controller层代码，用来验证与Controller相关的url路径映射、文件上传、参数绑定、校验等，通过@WebMvcTest完成mvc单元测试
//需要测试的Controller类
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	//MockMvc是spring提供的专门用于测试spring mvc的类
	private MockMvc mvc;
	//@MockBean用来模拟实现，因为在sping mvc测试中，spring容器并不会初始化@Service，@Compenent注解的类，因此我们需要模拟UserController调用的所有Service
	@MockBean
	private UserService userService;

	@Test
	public void testGetCredit() throws Exception {
		int expectedCredit = 200;
		given(userService.getCredit(1)).willReturn(expectedCredit);
		//perform完成一次mvc调用，spring mvc test是servlet容器内的一种模拟测试，并不会发起一次真正的http调用
		//get模拟一次get请求，地址是/user/credit/{id}，这里的{name}会被后面的参数代替
		//andExpect表示期望的返回结果，比如返回的内容或者http响应头
		mvc.perform(get("/user/credit/{id}", 1)).andExpect(content().string(String.valueOf(expectedCredit)));
	}

	@Test
	//模拟请求
	public void test() throws Exception {
		//模拟提交message参数
		mvc.perform(post("/user/{id}/{name}", 1, "李四").param("message", "hello"));
		//模拟提交checkbox
		mvc.perform(post("/user/{id}/{name}", 1, "李四").param("job", "IT", "SALE"));
		//使用Map构造参数
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("message", "hello");
		params.add("job", "IT");
		params.add("job", "SALE");
		mvc.perform(post("/user/{id}/{name}", 1, "李四").params(params));
		//模拟session和cookie
		mvc.perform(get("/user.html").sessionAttr("name", "李四"));
		mvc.perform(get("/user.html").cookie(new Cookie("name", "李四")));
		//设置http body内容，比如提交的json
		String json = "...省略了...";
		mvc.perform(get("/user.html").content(json));
		//设置http header
		mvc.perform(get("/user.html")
		//http提交内容
		.contentType("application/x-www-form-urlencoded")
		//期望返回内容
		.accept("application/json")
		//设置http头
		.header("xx", "xx"));
	}

	@Test
	//模拟返回
	public void test2() throws Exception {
		mvc.perform(get("/user/1"))
		//期望成功调用
		.andExpect(status().isOk())
		//期望返回的内容是json
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		//检查返回内容
		.andExpect(jsonPath("$.name").value("Jason"));

		//也可以对返回的ModelAndView进行校验
		mvc.perform(post("/form")).andExpect(view().name("/success.btl"));

		//比较Model
		mvc.perform(post("/form")).andExpect(status().isOk()).andExpect(model().size(1))
		.andExpect(model().attributeExists("person"))
		.andExpect(model().attribute("person", "zsl"));

		//比较forward或者redirect
		mvc.perform(post("/login")).andExpect(forwardedUrl("/index.html"));
		mvc.perform(post("/login")).andExpect(redirectedUrl("/index.html"));

		//比较返回内容，使用content()
		mvc.perform(post("/login")).andExpect(content().string("hello world"));
		//返回的内容是xml，并且与xmlContent一样
		mvc.perform(post("/login")).andExpect(content().xml("...省略..."));
		mvc.perform(post("/login")).andExpect(content().json("...省略..."));
		mvc.perform(post("/login")).andExpect(content().bytes("省略".getBytes()));
	}

}

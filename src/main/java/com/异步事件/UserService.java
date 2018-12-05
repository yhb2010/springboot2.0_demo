package com.异步事件;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.事件.UserBean;
import com.事件.UserRegisterEvent;

@Service
public class UserService {
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * 用户注册方法
	 *
	 * @param user
	 */
	public void register(UserBean user) {
		// ../省略其他逻辑
		// 发布UserRegisterEvent事件
		applicationContext.publishEvent(new UserRegisterEvent(this, user));
	}
}
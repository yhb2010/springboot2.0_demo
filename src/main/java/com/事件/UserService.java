package com.事件;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

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
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 发布UserRegisterEvent事件
		applicationContext.publishEvent(new UserRegisterEvent(this, user));
	}
}
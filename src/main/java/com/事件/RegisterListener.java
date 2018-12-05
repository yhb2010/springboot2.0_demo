package com.事件;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//这种方式也是Spring之前比较常用的监听事件方式，在实现ApplicationListener接口时需要将监听事件作为泛型传递，监听实现代码如下所示：
@Component
public class RegisterListener implements ApplicationListener<UserRegisterEvent> {
	/**
	 * 实现监听
	 * 我们实现接口后需要使用@Component注解来声明该监听需要被Spring注入管理，当有UserRegisterEvent事件发布时监听程序会自动调用onApplicationEvent方法并且将UserRegisterEvent对象作为参数传递。
	 * @param userRegisterEvent
	 */
	@Override
	public void onApplicationEvent(UserRegisterEvent userRegisterEvent) {
		// 获取注册用户对象
		UserBean user = userRegisterEvent.getUser();

		// ../省略逻辑

		// 输出注册用户信息
		System.out.println("ApplicationListener注册信息2，用户名：" + user.getName() + "，密码：" + user.getPassword());
	}
}
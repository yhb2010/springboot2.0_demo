package com.事件;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//注解方式比较简单，并不需要实现任何接口，具体代码实现如下所示：
@Component
public class AnnotationRegisterListener {

	/**
	 * 注册监听实现方法
	 * 我们只需要让我们的监听类被Spring所管理即可，在我们用户注册监听实现方法上添加@EventListener注解，该注解会根据方法内配置的事件完成监听。
	 * @param userRegisterEvent
	 *            用户注册事件
	 */
	@EventListener
	public void register(UserRegisterEvent userRegisterEvent) {
		// 获取注册用户对象
		UserBean user = userRegisterEvent.getUser();

		// ../省略逻辑

		// 输出注册用户信息
		System.out.println("@EventListener注册信息，用户名：" + user.getName() + "，密码：" + user.getPassword());
	}
}
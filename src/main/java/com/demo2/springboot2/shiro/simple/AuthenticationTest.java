package com.demo2.springboot2.shiro.simple;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;

public class AuthenticationTest {

	//认证过程
	private static void auth(){
		//类似数据源，可以得到用户认证信息等
		SimpleAccountRealm realm = new SimpleAccountRealm();
		//第三个参数：用户的角色，可以多个，可以没有
		realm.addAccount("zsl", "123456", "admin");

		//构建SecurityManager环境
		DefaultSecurityManager manager = new DefaultSecurityManager();
		manager.setRealm(realm);
		//主体提交认证请求
		SecurityUtils.setSecurityManager(manager);
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken("zsl", "123456");
		subject.login(token);

		System.out.println(subject.isAuthenticated());

		//判断是否有admin角色
		subject.checkRole("admin");
		//判断是否有多个角色
		//subject.checkRoles("admin");
	}

	public static void main(String[] args) {
		auth();
	}

}

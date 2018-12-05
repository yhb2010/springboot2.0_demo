package com.demo2.springboot2.shiro.customer;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

//从数据库获取数据
public class AuthenticationCustomerTest {

	//认证过程
	private static void auth(){
		CustomerRealm realm = new CustomerRealm();

		DefaultSecurityManager manager = new DefaultSecurityManager();
		manager.setRealm(realm);

		HashedCredentialsMatcher match = new HashedCredentialsMatcher();
		match.setHashAlgorithmName("md5");
		match.setHashIterations(1024);
		realm.setCredentialsMatcher(match);

		SecurityUtils.setSecurityManager(manager);
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken("张三", "123456");
		subject.login(token);

		System.out.println(subject.isAuthenticated());
		System.out.println(subject.hasRole("管理员"));
		//是否有多个权限
		subject.checkPermissions(new String[]{"user:delete", "user:update"});
	}

	public static void main(String[] args) {
		auth();
	}

}

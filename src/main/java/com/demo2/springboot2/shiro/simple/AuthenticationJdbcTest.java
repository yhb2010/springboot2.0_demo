package com.demo2.springboot2.shiro.simple;

import javax.sql.DataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import com.zaxxer.hikari.HikariDataSource;

//从数据库获取数据
public class AuthenticationJdbcTest {

	public static DataSource datasource(){
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl("jdbc:mysql://127.0.0.87:port/dbName?allowMultiQueries=true");
		ds.setUsername("123");
		ds.setPassword("123");
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		return ds;
	}

	//认证过程
	private static void auth(){
		JdbcRealm realm = new JdbcRealm();
		realm.setDataSource(datasource());
		//为true才会查询权限数据
		realm.setPermissionsLookupEnabled(true);

		String authenticationQuery = "select password from user where name = ?";
		realm.setAuthenticationQuery(authenticationQuery);
		String userRolesQuery = "select rolename from user_role where name = ?";
		realm.setUserRolesQuery(userRolesQuery);
		String permissionsQuery = "select permission from rolespermission where rolename=?";
		realm.setPermissionsQuery(permissionsQuery);

		DefaultSecurityManager manager = new DefaultSecurityManager();
		manager.setRealm(realm);
		SecurityUtils.setSecurityManager(manager);
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken("李四", "123456");
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

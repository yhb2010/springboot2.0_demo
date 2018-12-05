package com.demo2.springboot2.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**自定义过滤器：只要用户所属角色和角色列表里有一个匹配就返回true
 * @author DELL
 *
 */
public class RoleOrFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object object) throws Exception {
		Subject subject = getSubject(request, response);
		String[] roles = (String[])object;
		if(roles == null || roles.length == 0){
			return true;
		}
		for (String role : roles) {
			if(subject.hasRole(role)){
				return true;
			}
		}
		return false;
	}

}

package com.demo2.springboot2.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**自定义权限数据源
 * @author DELL
 *
 */
public class CustomerRealm extends AuthorizingRealm {

	@Autowired
	public ShiroService shiroService;

	@Override
    public String getName() {
        return "customRealm1";
    }

	@Override
	//授权
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 获取身份信息
        String username = (String) principals.getPrimaryPrincipal();
        // 根据身份信息从数据库中查询权限数据
        String password = shiroService.getPasswordByUsername(username);
		if(password == null){
			return null;
		}
        //....这里使用静态数据模拟
        List<String> permissions = new ArrayList<String>();
        permissions.add("user:create");
        permissions.add("user:update");
        permissions.add("user:delete");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(new HashSet<String>(){{
        	add("管理员");
        }});
        for(String permission : permissions){
            simpleAuthorizationInfo.addStringPermission(permission);
        }

        return simpleAuthorizationInfo;
	}

	@Override
	//认证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationtoken) throws AuthenticationException {
		//先从主体传过来的认证信息中，获取用户名
		String username = (String)authenticationtoken.getPrincipal();
		//通过用户名去数据库获取密码
		System.out.println("从数据库获取结果");
		String password = shiroService.getPasswordByUsername(username);
		if(password == null){
			return null;
		}
		// credentialsSalt盐值
	    ByteSource credentialsSalt = ByteSource.Util.bytes(username);//使用账号作为盐值
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, new MySimpleByteSource(username.getBytes()), getName());
		return info;
	}

}

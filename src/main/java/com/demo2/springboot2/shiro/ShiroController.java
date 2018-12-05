package com.demo2.springboot2.shiro;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiroController {

	@PostMapping("/shiro/doLogin")
    public String doLogin(@RequestBody Map<String, String> user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.get("username"), user.get("password"));
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            token.clear();
            return "登录失败，用户名或密码错误！";
        }
        return "登录成功";
    }

	@PostMapping("/shiro/list")
    public String getList() {
        return "列表内容";
    }

	@RequiresRoles("管理员")
	@PostMapping("/shiro/anno/list")
	public String getAnnoList() {
		return "列表内容";
	}

	@RequiresPermissions("user:create")
	@PostMapping("/shiro/anno/list2")
	public String getAnnoList2() {
		return "列表内容";
	}

	@RequiresPermissions("user:list")
	@PostMapping("/shiro/anno/list3")
	public String getAnnoList3() {
		return "列表内容";
	}

	@PostMapping("/shiro/self")
	public String self() {
		return "列表内容";
	}

}

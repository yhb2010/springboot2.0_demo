package com.shopcart;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdel.redis.store.RedisUtil;

public class RequestUtils {

	// 获取CSessionID
	public static String getCSessionId(HttpServletRequest request, HttpServletResponse response) {
		// 1, 从Request中取Cookie
		Cookie[] cookies = request.getCookies();
		// 2, 从Cookie数据中遍历查找, 并取CSessionID
		if (null != cookies && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if ("CSESSIONID".equals(cookie.getName())) {
					// 有, 直接返回
					return cookie.getValue();
				}
			}
		}
		// 没有, 创建一个CSessionId, 并且放到Cookie再返回浏览器.返回新的CSessionID
		String csessionid = UUID.randomUUID().toString().replaceAll("-", "");
		// 并且放到Cookie中
		Cookie cookie = new Cookie("CSESSIONID", csessionid);
		// cookie 每次都带来, 设置路径
		cookie.setPath("/");
		// 0:关闭浏览器 销毁cookie. 0:立即消失. >0 存活时间,秒
		cookie.setMaxAge(24 * 60 * 60);
		response.addCookie(cookie);

		return csessionid;
	}

	//获取
	public static String getAttributterForUsername(String jessionId){
	    String value = RedisUtil.get("util_demo", jessionId + ":USER_NAME");
	    if(null != value){
	        //计算session过期时间是 用户最后一次请求开始计时.
	    	RedisUtil.expire("util_demo", jessionId + ":USER_NAME", 60*1000);
	        return value;
	    }else{
	    	//模拟登录
	    	RedisUtil.set("util_demo", jessionId + ":USER_NAME", "zsl");
	    }
	    return null;
	 }

}
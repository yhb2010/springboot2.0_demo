package com.demo.springboot2.c3mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demo.springboot2.domain.User;

public class SessionHandlerInterceptor implements HandlerInterceptor {
    @Override
    //调用controller方法前
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (httpServletRequest.getHeader("x-requested-with") != null && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
            //如果是ajax请求响应头会有，x-requested-with；
            HttpSession session = httpServletRequest.getSession();
            User profile = (User)session.getAttribute("user");
            System.out.println("SessionHandlerInterceptor: "+profile);
            if (profile == null){//判断session里是否有用户信息
                //httpServletResponse.setHeader("sessionstatus", "timeout");//在响应头设置session状态
                httpServletResponse.getWriter().write("{'sessionstatus':'timeout'}");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    	//方法处理完毕后，页面渲染前，调用此方法，比如在这里可以修改视图名称
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    	//页面渲染完毕后，调用此方法，通常用来清除某些资源，类似java的finally
    }
}
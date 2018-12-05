package com.demo2.springboot2.shiro;

import org.springframework.stereotype.Service;

@Service
//新建一个服务，用来模拟从数据库中取出用户信息
public class ShiroService {

    public String getPasswordByUsername(String username){
        switch (username){
            case "张三":
                return "d6ae580c9bbbe9802d8e785a09a9f5b0";
            case "hanli":
                return "456";
            default:
                return null;
        }
    }
}

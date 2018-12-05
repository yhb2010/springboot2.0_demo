package com.事件;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

//我们先来创建一个事件，监听都是围绕着事件来挂起的。事件代码如下所示：
//我们自定义事件UserRegisterEvent继承了ApplicationEvent，继承后必须重载构造函数，构造函数的参数可以任意指定，其中source参数指的是发生事件的对象，一般我们在发布事件时使用的是this关键字代替本类对象，而user参数是我们自定义的注册用户对象，该对象可以在监听内被获取。
//在Spring内部中有多种方式实现监听如：@EventListener注解、实现ApplicationListener泛型接口、实现SmartApplicationListener接口等，我们下面来讲解下这三种方式分别如何实现。
@Getter
public class UserRegisterEvent extends ApplicationEvent {

	//注册用户对象
    private UserBean user;

    /**
     * 重写构造函数
     * @param source 发生事件的对象
     * @param user 注册用户对象
     */
    public UserRegisterEvent(Object source, UserBean user) {
        super(source);
        this.user = user;
    }

}
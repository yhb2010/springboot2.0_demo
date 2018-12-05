package com.ioc容器;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

import com.domain.Business;

public class BeanFactoryDemo {

	//BeanDefinition 对象就承担了这个责任：容器中的每一个 bean 都会有一个对应的 BeanDefinition 实例。
	//该实例负责保存 bean 对象的所有必要信息，包括 bean 对象的 class 类型、是否是抽象类、构造方法和参数、其他属性等等。
	//当客户端向容器请求相应对象时，容器就会通过这些信息为客户端返回一个完整可用的 bean 实例。
	//原材料已经准备好（把 BeanDefinition 看做原料），开始做菜吧，等等，你还需要一份菜谱。

	//BeanDefinitionRegistry 和 BeanFactory 就是这份菜谱，BeanDefinitionRegistry 抽象出 bean 的注册逻辑。
	//而 BeanFactory 则抽象出了 bean 的管理逻辑，而各个 BeanFactory 的实现类就具体承担了 bean 的注册以及管理工作。

	//DefaultListableBeanFactory 作为一个比较通用的 BeanFactory 实现，它同时也实现了 BeanDefinitionRegistry 接口，因此它就承担了 bean 的注册管理工作。
	//BeanFactory 接口中主要包含 getBean、containBean、getType、getAliases 等管理 bean 的方法。
	//而 BeanDefinitionRegistry 接口则包含 registerBeanDefinition、removeBeanDefinition、getBeanDefinition 等注册管理 BeanDefinition 的方法。
	public static void main(String[] args) {
		//默认容器实现
		DefaultListableBeanFactory beanRegistery = new DefaultListableBeanFactory();
		//根据业务对象构造相应的BeanDefinition
		AbstractBeanDefinition definition = new RootBeanDefinition(Business.class);
		//将bean定义注册到容器中
		beanRegistery.registerBeanDefinition("business", definition);
		//...
		//然后可以从容器中获取这个bean的实例
		//这里的beanRegistery其实实现了BeanFactory接口，可以强转
		//单纯的BeanDefinitionRegistery无法强制转换到BeanFactory
		BeanFactory container = (BeanFactory)beanRegistery;
		Business b = (Business)container.getBean("business");
		System.out.println(b);
	}

}

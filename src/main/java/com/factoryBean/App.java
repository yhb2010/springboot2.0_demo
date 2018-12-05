package com.factoryBean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
Spring中的Bean有两种。
一种是普通的bean ，比如配置
<bean id="personService" class="com.spring.service.impl.PersonServiceImpl" scope="prototype">
	<property name="name" value="is_zhoufeng" />
</bean>
那个使用BeanFactory根据id personService获取bean的时候，得到的对象就是PersonServiceImpl类型的。

另外一种就是实现了org.springframework.beans.factory.FactoryBean<T>接口的Bean ， 那么在从BeanFactory中根据定义的id获取bean的时候，获取的实际上是FactoryBean接口中的getObject()方法返回的对象。
以Spring提供的ProxyFactoryBean为例子，配置如下：
<bean id="personServiceByLog" class="org.springframework.aop.framework.ProxyFactoryBean">
	<property name="proxyInterfaces">
		<list>
			<value>com.spring.service.PersonService</value>
		</list>
	</property>
	<property name="interceptorNames">
		<list>
			<value>logInteceptor</value>
			<value>ZFMethodAdvice</value>
		</list>
	</property>
	<property name="targetName" value="personService" />
</bean>
那么在代码中根据personServiceByLog来获取的Bean实际上是PersonService类型的。
PersonService ps = context.getBean("personServiceByLog", PersonService.class);
如果要获取ProxyFactoryBean本身，可以如下
ProxyFactoryBean factoryBean = context.getBean("&personServiceByLog", ProxyFactoryBean.class);
PersonService ps = (PersonService) factoryBean.getObject();

自己实现一个FactoryBean， 功能：用来代理一个对象，对该对象的所有方法做一个拦截，在方法调用前后都输出一行log
通过FactoryBean这种特点，可以实现很多有用的功能 。。。
 * @author DELL
 *
 */
public class App {

	public static void main( String[] args ) {
        // 以注解的形式把 bean 注入Spring 并获取 Spring 的上下文环境
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);
        // 获取自己配置的 bean 实例
        PersonService personService = ctx.getBean(PersonService.class);
        try {
        	String name = personService.getNameForDB();
            System.out.println("name ----- >> " + name);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

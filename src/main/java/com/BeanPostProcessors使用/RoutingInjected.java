package com.BeanPostProcessors使用;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**RoutingInjected的作用类似于我们常用的Autowired，声明了该注解的属性将会被注入一个路由代理类实例；
 * @author DELL
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RoutingInjected{

}
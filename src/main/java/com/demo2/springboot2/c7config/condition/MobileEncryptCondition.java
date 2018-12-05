package com.demo2.springboot2.c7config.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Configuration
public class MobileEncryptCondition {

	static class EncryptCondition implements Condition{

		//这是一个对存入数据库的用户手机进行加密的类，使用了@condition注解，要求当存在salt.txt文件且配置允许手机加密时才生效
		@Bean
		@Conditional(EncryptCondition.class)
		public MobileEncryptBean mobileEncryptBean(){
			return new MobileEncryptBean();
		}

		@Override
		//ConditionContext类可以得到：
		//Environment可以读取系统属性，环境变量，配置参数等作为判断条件，比如当文件中某一项存在的时候配置生效
		//ResourceLoader：一个spring类，用来加载和判断资源文件，比如当某个配置文件存在时配置才生效
		//ConfigurableListableBeanFactory:spring容器
		public boolean matches(ConditionContext ctx, AnnotatedTypeMetadata matadata) {
			Resource res = ctx.getResourceLoader().getResource("salt.txt");
			Environment env = ctx.getEnvironment();
			return res.exists() && env.containsProperty("mobile.encrypt.enable");
		}

	}

}

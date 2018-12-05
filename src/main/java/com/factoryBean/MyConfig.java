package com.factoryBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    public PersonService personService(){
    	PersonService personService = new PersonServiceImpl();
    	return personService;
    }

    @Bean
    public MyFactoryBean myFactoryBean(){
    	MyFactoryBean bean = new MyFactoryBean();
    	bean.setInterfaceName("com.factoryBean.PersonService");
    	bean.setTarget(personService());
    	return bean;
    }

}

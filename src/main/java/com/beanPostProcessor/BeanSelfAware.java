package com.beanPostProcessor;

//使用了@EnableClassInnerProxy注解后，就可以不用这个类了
//定义BeanPostProcessor 需要使用的标识接口
public interface BeanSelfAware {

	public abstract void setSelf(Object obj);

}
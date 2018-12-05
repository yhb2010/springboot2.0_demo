package com.beanPostProcessor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

//使用了@EnableClassInnerProxy注解后，就可以不用这个类了
//@Service
public class InjectBeanSelfProcessor implements BeanPostProcessor, ApplicationContextAware {
	ApplicationContext context;
	private static Log log = LogFactory.getLog(InjectBeanSelfProcessor.class);

	public InjectBeanSelfProcessor() {
	}

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof BeanSelfAware) {// 如果Bean实现了BeanSelfAware标识接口，就将代理对象注入
			BeanSelfAware myBean = (BeanSelfAware) bean;
			if (!AopUtils.isAopProxy(bean)) {
				Class c = bean.getClass();
				Service serviceAnnotation = (Service) c.getAnnotation(Service.class);
				if (serviceAnnotation != null)
					try {
						bean = context.getBean(beanName);
						if (AopUtils.isAopProxy(bean)){
							myBean.setSelf(bean);
							return myBean;
						}
					} catch (BeanCurrentlyInCreationException beancurrentlyincreationexception) {
					} catch (Exception ex) {
						log.fatal(
								(new StringBuilder())
										.append("No Proxy Bean for service ")
										.append(bean.getClass()).append(" ")
										.append(ex.getMessage()).toString(), ex);
					}
			}
			myBean.setSelf(bean);
			return myBean;
		} else {
			return bean;
		}
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}
}
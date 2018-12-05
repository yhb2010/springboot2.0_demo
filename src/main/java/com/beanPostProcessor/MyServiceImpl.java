package com.beanPostProcessor;

import org.springframework.stereotype.Service;
import com.cdel.util.anno.method.Async;
import com.cdel.util.service.BeanSelfAware;

@Service
public class MyServiceImpl implements MyService, BeanSelfAware {

	private MyService myService;

	public void setSelf(Object proxyBean) { //通过InjectBeanSelfProcessor注入自己（目标对象）的AOP代理对象
	    this.myService = (MyService) proxyBean;
	}

	@Override
	public void a() {
		myService.b();
	}

	@Override
	@Async
	public void b() {

	}

}

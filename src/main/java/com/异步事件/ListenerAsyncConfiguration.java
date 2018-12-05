package com.异步事件;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

//@Aysnc其实是Spring内的一个组件，可以完成对类内单个或者多个方法实现异步调用，这样可以大大的节省等待耗时。内部实现机制是线程池任务ThreadPoolTaskExecutor，通过线程池来对配置@Async的方法或者类做出执行动作。
//我们创建一个ListenerAsyncConfiguration，并且使用@EnableAsync注解开启支持异步处理，具体代码如下所示：
//我们自定义的监听异步配置类实现了AsyncConfigurer接口并且实现内getAsyncExecutor方法以提供线程任务池对象的获取。
//我们只需要在异步方法上添加@Async注解就可以实现方法的异步调用
@Configuration
@EnableAsync
public class ListenerAsyncConfiguration implements AsyncConfigurer {
	/**
	 * 获取异步线程池执行对象
	 *
	 * @return
	 */
	@Override
	public Executor getAsyncExecutor() {
		// 使用Spring内置线程池任务对象
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		// 设置线程池参数
		taskExecutor.setCorePoolSize(5);
		taskExecutor.setMaxPoolSize(10);
		taskExecutor.setQueueCapacity(25);
		taskExecutor.initialize();
		return taskExecutor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return null;
	}

}
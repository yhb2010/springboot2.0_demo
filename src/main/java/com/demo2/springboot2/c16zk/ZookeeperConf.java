package com.demo2.springboot2.c16zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperConf {

	@Value("${zk.url}")
	private String zkUrl;
	@Value("${zk.basePath}")
	private String basePath;
	@Value("${zk.masterPath}")
	private String masterPath;

	@Bean
	public CuratorFramework getCuratorFramework(){
		//初始化连接
		//ExponentialBackoffRetry是一种重试策略，每次重连的时间间隔会越来越长，1000毫秒是初始化的间隔时间，3代表尝试重试次数
		CuratorFramework client = CuratorFrameworkFactory.newClient(zkUrl, new ExponentialBackoffRetry(1000, 3));
		//启动一个客户端
        client.start();
        return client;
	}

	@Bean
	public ServiceRegistry getServiceRegistry(CuratorFramework client) throws Exception{
		ServiceRegistry registry = new ServiceRegistry(client, basePath);
		registry.start();
		return registry;
	}

	@Bean
	public LeaderSelector master(CuratorFramework client){
		LeaderSelector selector = new LeaderSelector(client,
				masterPath,
        		//会在获取master权利后，回调该监听。需要注意的是，一旦执行完takeLeadership方法，curator就会立即释放
        		//master权利，然后重新开始新一轮的master选举。如果只有一个节点，这段代码会导致该节点不断成为主节点再释放主节点
        		new LeaderSelectorListenerAdapter() {
		            public void takeLeadership(CuratorFramework client) throws Exception {
		                System.out.println("成为Master角色");
		                Thread.sleep(3000);
		                System.out.println( "完成Master操作，释放Master权利" );
		            }
		        });
        selector.autoRequeue();
        selector.start();
        return selector;
	}

}

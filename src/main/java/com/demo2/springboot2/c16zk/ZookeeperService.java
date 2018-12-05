package com.demo2.springboot2.c16zk;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZookeeperService {

	@Autowired
	private CuratorFramework zkClient;
	String lockPath = "/lock/order";

	//处理某种订单类型，比如type值是book
	//我们需要实现在makeOrderType方法上加锁，确保同一时间只能有一个应用能执行同次订单类型号相关的操作，其他应用处于等待状态
	public void makeOrderType(String type){
		//首先确定分布式锁的路径
		String path = lockPath + "/" + type;
		System.out.println("try do job for " + type);
		try{
			//这样，curator会在此节点下创建一系列临时的序列节点，并选择序列号最小的节点作为锁的拥有者
			//InterProcessMutex创建一个分布式锁，调用acquire来获取锁操作，并传入一个等待时间
			InterProcessMutex lock = new InterProcessMutex(zkClient, path);
			if(lock.acquire(10, TimeUnit.HOURS)){
				try{
					//模拟耗时5秒
					Thread.sleep(1000*5);
					//即时获得分布式锁，也要在实际业务中检查数据是否已经被处理了
					System.out.println("do job " + type + " done");
				}finally{
					lock.release();
				}
			}
		}catch(Exception e){
			//zk异常
			e.printStackTrace();
		}
	}

}

package com.demo2.springboot2.c17actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

import com.zaxxer.hikari.HikariConfigMXBean;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;

//@Endpoint定义一个监控类
@Endpoint(id="hikaricp")
public class HikariCPEndpoint {

	HikariDataSource ds;

	public HikariCPEndpoint(HikariDataSource ds){
		this.ds = ds;
	}

	//@ReadOperation显示监控信息
	@ReadOperation
	public Map<String, Object> info(){
		Map<String, Object> map = new HashMap<>();
		//连接池配置
		HikariConfigMXBean configBean = ds.getHikariConfigMXBean();
		map.put("total", configBean.getMaximumPoolSize());
		//连接池运行状态
		HikariPoolMXBean mxBean = ds.getHikariPoolMXBean();
		map.put("active", mxBean.getActiveConnections());
		map.put("idle", mxBean.getIdleConnections());
		//连接都被使用情况下，等待获取连接的线程个数
		map.put("threadWait", mxBean.getThreadsAwaitingConnection());
		return map;
	}

	//使用注解@WriteOperation可以用http post的方式动态更改监控指标，如下代码会动态调整数据连接池的最大连接数。
	//使用curl发起：curl -XPOST 'http://127.0.0.10:8083/manage/hikaricp' -H 'Content-Type:application/json' -d'
	//{"max":7}
	@WriteOperation
	public void setMax(int max){
		ds.getHikariConfigMXBean().setMaximumPoolSize(max);
	}

}

package com.demo2.springboot2.c17actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**health用于检测springboot应用健康状态，提供了磁盘的健康状态显示，如果还使用了数据源、nosql等，也会显示相应的健康状态。
 * 使用HealthIndicator接口实现健康信息显示，默认有下列实现：
 * DiskSpaceHealthIndicator：检测磁盘空间
 * DataSourceHealthIndicator：检测数据源是否可用，数据源对应的数据库版本信息
 * XXXHealthIndicator：XXX代表了内置的Elasticsearch、jms、mail、mongodb、rabbit、redis、solr等，以验证这些服务是否可用
 *
 * 编写自己的监控器，只需要实现HealthIndicator接口的health方法，返回一个Health对象即可。
 * Health对象的up方法表示健康，对象正常，down表示异常，可以通过withDetail添加任意多的信息，对象的名称默认是去掉HealthIndicator后缀。
 * @author DELL
 *
 */
@Component
public class MessageCenterHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		int errorCode = check();
		if(errorCode != 0){
			return Health.down().withDetail("Message", "error:" + errorCode).build();
		}
		return Health.up().build();
	}

	protected int check() {
		//模拟返回一个错误状态
		return 1;
	}

}

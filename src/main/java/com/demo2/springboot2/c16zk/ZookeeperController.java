package com.demo2.springboot2.c16zk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZookeeperController {

	@Autowired
	private ZookeeperService zookeeperService;
	@Autowired
	private ServiceRegistry serviceRegistry;

	@GetMapping("/zk/lock")
	public void makeOrderType(String type){
		zookeeperService.makeOrderType("book");
	}

	/**注册实例1
	 * @param type
	 * @throws Exception
	 */
	@GetMapping("/zk/register1")
	public void register1() throws Exception{
		//是一个描述服务的类，address方法设置服务地址，如果没有调用，设置本机地址，port设置了服务端口，name设置了服务名字
		Map map = new HashMap();
		map.put("url", "/api/v3/qzchapter");
		ServiceInstance<Map> host = ServiceInstance.<Map>builder()
                .name("qzchapter")
                .port(21888)
                .address("192.168.1.100")
                .payload(map)
                .uriSpec(new UriSpec("{scheme}://{address}:{port}"))
                .build();
		serviceRegistry.registerService(host);
	}

	/**注册实例2
	 * @param type
	 * @throws Exception
	 */
	@GetMapping("/zk/register2")
	public void register2() throws Exception{
		Map map = new HashMap();
		map.put("url", "/api/v4/qzchapter");
		ServiceInstance<Map> host = ServiceInstance.<Map>builder()
                .name("qzchapter")
                .port(21889)
                .address("192.168.1.101")
                .payload(map)
                .uriSpec(new UriSpec("{scheme}://{address}:{port}"))
                .build();
		serviceRegistry.registerService(host);
	}

	/**获取服务
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/zk/getservice")
	public void getService() throws Exception{
		Collection<ServiceInstance<Map>> list = serviceRegistry.queryForInstances("qzchapter");
		//这个例子只是取得第一个实例，实际中可以采用轮询
        if(list!=null && list.size()>0){
        	ServiceInstance<Map> service = new ArrayList<ServiceInstance<Map>>(list).get(0);
            System.out.println(service.getAddress());
            System.out.println(service.getPayload());
        } else {
            System.out.println("service:qzchapter provider is empty...");
        }

	}

}

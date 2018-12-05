package com.demo2.springboot2.c16zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

import java.util.Collection;
import java.util.Map;

/**服务注册
 * @author DELL
 *
 */
public class ServiceRegistry {

	private ServiceDiscovery<Map> serviceDiscovery;

    private final CuratorFramework client;

    //所有服务注册到/basePath节点上，是服务注册的根节点
    public ServiceRegistry(CuratorFramework client, String basePath){
        this.client = client;
        serviceDiscovery = ServiceDiscoveryBuilder.builder(Map.class)
                .client(client)
                .serializer(new JsonInstanceSerializer<>(Map.class))
                .basePath(basePath)
                .build();
    }

    public void updateService(ServiceInstance<Map> instance) throws Exception {
        serviceDiscovery.updateService(instance);
    }

    public void registerService(ServiceInstance<Map> instance) throws Exception {
        serviceDiscovery.registerService(instance);
    }

    public void unregisterService(ServiceInstance<Map> instance) throws Exception {
        serviceDiscovery.unregisterService(instance);
    }

    public Collection<ServiceInstance<Map>> queryForInstances(String name) throws Exception {
        return serviceDiscovery.queryForInstances(name);
    }

    public ServiceInstance<Map> queryForInstance(String name, String id) throws Exception {
        return serviceDiscovery.queryForInstance(name, id);
    }

    public void start() throws Exception {
        serviceDiscovery.start();
    }

    public void close() throws Exception {
        serviceDiscovery.close();
    }

}

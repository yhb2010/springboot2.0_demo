package com.springFactoriesLoader;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

public class Test {

	//从 CLASSPATH 下的每个 jar 包中搜寻所有 META-INF/spring.factories 配置文件，然后将解析 properties 文件，找到指定名称的配置后返回。
	//需要注意的是，其实这里不仅仅是会去 ClassPath 路径下查找，会扫描所有路径下的 jar 包，只不过这个文件只会在 ClassPath 下的 jar 包中。
	//执行 loadFactoryNames(EnableAutoConfiguration.class,classLoader)后，得到对应的一组 @Configuration 类。
	//我们就可以通过反射实例化这些类然后注入到 IOC 容器中，最后容器里就有了一系列标注了 @Configuration 的 JavaConfig 形式的配置类。
	//这就是 SpringFactoriesLoader，它本质上属于 Spring 框架私有的一种扩展方案，类似于 SPI，Spring Boot 在 Spring 基础上的很多核心功能都是基于此
	public static void main(String[] args) throws IOException {
		Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getSystemResources("META-INF/spring.factories");
		Map<String, List> result = new HashMap<>();
		while(urls.hasMoreElements()) {
            URL url = (URL)urls.nextElement();
            UrlResource resource = new UrlResource(url);
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            Iterator iterator = properties.entrySet().iterator();
            while(iterator.hasNext())
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                List factoryClassNames = Arrays.asList(StringUtils.commaDelimitedListToStringArray((String)entry.getValue()));
                result.putIfAbsent((String)entry.getKey(), factoryClassNames);
            }
        }
	}

}

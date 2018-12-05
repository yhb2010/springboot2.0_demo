package com.beanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**spring aware的目的是为了让bean获取spring容器的服务，接口如下所示：
 * BeanNameAware：获取容器中bean的名称
 * BeanFactoryAware：获得当前bean factory，这样可以调用容器的服务
 * ApplicationContextAware：当前的application context，这样可以调用容器的服务
 * MessageSourceAware：获取的message source，这样可以获得文本信息
 * ApplicationEventPublisherAware：应用事件发布器，可以发布事件
 * ResourceLoaderAware：获得资源加载器，可以获得外部资源文件
 * @author Administrator
 *
 */
public class BeanFactoryPostProcessorTest implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware {

	private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public void setBeanFactory(BeanFactory paramBeanFactory) throws BeansException {
        System.out.println("》》》调用了BeanFactoryAware的setBeanFactory方法了");
    }

    @Override
    public void setBeanName(String paramString) {
        System.out.println("》》》调用了BeanNameAware的setBeanName方法了：" + paramString);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("》》》调用了DisposableBean的destroy方法了");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("》》》调用了Initailization的afterPropertiesSet方法了");
    }

    @Override
    public String toString() {
        return "BeanFactoryPostProcessorTest [name=" + name + ", sex=" + sex + "]";
    }

}

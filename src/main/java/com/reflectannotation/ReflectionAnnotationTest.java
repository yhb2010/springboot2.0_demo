package com.reflectannotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

//正常的获取注解属性值的场景:
@RunWith(SpringRunner.class)
public class ReflectionAnnotationTest {

    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        //获取Bar实例
        Bar bar = new Bar();
        //获取Bar的val字段
        Field field = bar.getClass().getDeclaredField("value");
        //获取val字段上的Foo注解实例
        //(看到foo栈中的属性h是一个AnnotationInvocationHandler类型的对象。h对象中包含一个memberValues对象，里面装着key就是我们自定义注解的属性，value就是我赋的值。)
        //(可以发现memberValues对象是private final修饰的。所以我们可以通过反射修改memberValues的访问权限，来打到修改memberValues值的目的。)
        //(所以动态修改注解的值的方法为：通过反射得到foo的代理对象，然后得到代理对象的memberValues属性，修改访问权限，更新注解的value属性值。)
        Foo foo = field.getAnnotation(Foo.class);
        //获取Foo注解实例的 value 属性值
        String value =  foo.value();
        //打印该值
        System.out.println("修改之前的注解值：" + value);
        
        System.out.println("------------以下是修改注解的值------------");
        
        //获取 foo 这个代理实例所持有的 InvocationHandler
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(foo);
        // 获取 AnnotationInvocationHandler 的 memberValues 字段
        Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
        // 因为这个字段事 private final 修饰，所以要打开权限
        declaredField.setAccessible(true);
        // 获取 memberValues
        Map memberValues = (Map) declaredField.get(invocationHandler);
        // 修改 value 属性值
        memberValues.put("value", "test.annotation.new.value");
        // 获取 foo 的 value 属性值
        String newValue = foo.value();
        System.out.println("修改之后的注解值：" + newValue);

    }

}

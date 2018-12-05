package com.reflectannotation;

//定义一个普通的Java对象 Bar，它有一个私有的String属性 value，并为它设置属性值为"test.annotation.value" 的 @Foo 注解
public class Bar {
    @Foo("test.annotation.value")
    private String value;
}
package com.demo2.springboot2.c9test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith是junit的标准注解，用来告诉junit单元测试框架不要使用内置的方式进行单元测试，而应使用RunWith指明的类来提供单元测试，所有的spring单元测试总是使用SpringRunner
@RunWith(SpringRunner.class)
//@SpringBootTest用于springboot测试，他默认会根据包名逐级往上查找，一直找到springboot主程序，也就是通过类注解是否包含@SpringBootApplication来判断是否是主程序，并在单元测试的时候启动该类来创建spring上下文环境
//spring单元测试并不会在每个单元测试前都启动一个spring上下文。它会缓存上下文环境，以提供给每个单元测试方法。如果你的单元测试改变了上下文，你需要在单元测试方法上加上@DirtiesContext以提示spring重新加载上下文
@SpringBootTest
public class UserServiceTest {



}

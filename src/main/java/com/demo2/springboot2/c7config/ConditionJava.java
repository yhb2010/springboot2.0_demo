package com.demo2.springboot2.c7config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava.Range;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Configuration;

@Configuration
//根据指定java版本
@ConditionalOnJava(range = Range.EQUAL_OR_NEWER, value = JavaVersion.EIGHT)
public class ConditionJava {


}

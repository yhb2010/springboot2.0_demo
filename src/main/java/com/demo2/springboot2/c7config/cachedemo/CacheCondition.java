package com.demo2.springboot2.c7config.cachedemo;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class CacheCondition implements Condition {

	@Override
	public boolean matches(ConditionContext conditioncontext, AnnotatedTypeMetadata annotatedtypemetadata) {
		return false;
	}

}

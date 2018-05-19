package com.lenicliu.spring.boot;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceRouter {
    private ThreadLocal<String> currentKey = new ThreadLocal<>();

    public String getCurrentKey() {
        return currentKey.get();
    }

    public static String routing(Class<?> clazz) {
        return clazz.getAnnotation(DataSourceRouting.class).db();
    }

    @Order(-1)
    @Around("execution(* com.lenicliu.spring.boot.mybatis.mapper.*.*(..))")
    public Object invoke(ProceedingJoinPoint point) throws Throwable {
        Method method = ((MethodSignature)point.getSignature()).getMethod();
        try {
            this.set(routing(method.getDeclaringClass()));
            return point.proceed();
        } finally {
            this.remove();
        }
    }

    public void set(String value) {
        currentKey.set(value);
    }

    public void remove() {
        currentKey.remove();
    }
}
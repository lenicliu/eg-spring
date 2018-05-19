package com.lenicliu.spring.boot;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceRouting {
    String db();
}
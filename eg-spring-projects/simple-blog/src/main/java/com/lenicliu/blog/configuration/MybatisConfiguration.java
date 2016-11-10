package com.lenicliu.blog.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lenicliu on 11/10/16.
 */
@Configuration
@MapperScan("com.lenicliu.blog.mapper")
public class MybatisConfiguration {
}
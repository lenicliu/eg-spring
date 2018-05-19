package com.lenicliu.spring.boot.mybatis.mapper;

import com.lenicliu.spring.boot.DataSourceRouting;
import com.lenicliu.spring.boot.domain.User;

@DataSourceRouting(db = "app")
public interface UserMapper {
    User find(Long id);
}
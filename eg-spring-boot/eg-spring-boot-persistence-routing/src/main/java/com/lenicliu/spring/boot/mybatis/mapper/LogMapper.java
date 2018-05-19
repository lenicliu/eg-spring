package com.lenicliu.spring.boot.mybatis.mapper;

import com.lenicliu.spring.boot.DataSourceRouting;
import com.lenicliu.spring.boot.domain.Log;

import java.util.List;

@DataSourceRouting(db = "log")
public interface LogMapper {
    List<Log> list(Long uid);
}
package com.lenicliu.blog.mapper;

import com.lenicliu.blog.model.Option;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by lenicliu on 11/10/16.
 */
public interface OptionMapper {
    @Select("SELECT * FROM OPTION WHERE NAME = #{name}")
    Option find(@Param("name") String name);

    @Select("INSERT INTO OPTION(NAME,VALUE)VALUES(#{name},#{value})")
    void insert(@Param("name") String name, @Param("value") String value);
}

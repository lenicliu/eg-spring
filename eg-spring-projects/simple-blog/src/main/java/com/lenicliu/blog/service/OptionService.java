package com.lenicliu.blog.service;

import com.lenicliu.blog.model.Option;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lenicliu on 11/10/16.
 */
public interface OptionService {
    String USERNAME = "username";
    String PASSWORD = "password";
    String PAGESIZE = "pagesize";

    List<String> NAMES = Arrays.asList(USERNAME, PASSWORD, PAGESIZE);

    Option find(String name);
}

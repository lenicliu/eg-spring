package com.lenicliu.blog.service;

import com.lenicliu.blog.model.Option;

/**
 * Created by lenicliu on 11/10/16.
 */
public interface OptionService {
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    Option find(String name);
}

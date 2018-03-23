package com.lenicliu.spring.boot;

public interface UserRepository {

    User getById(Long id);
}
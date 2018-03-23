package com.lenicliu.spring.boot;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User getById(Long id) {
        if (new Long(1L).equals(id)) {
            User user = new User();
            user.setId(1L);
            user.setUsername("lenicliu");
            user.setAddressId(1L);
            return user;
        }
        return null;
    }
}
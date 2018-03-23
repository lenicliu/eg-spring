package com.lenicliu.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * @author lenicliu
 */
@Configurable
public class User {
    private Long id;
    private String username;
    private Long addressId;

    @Autowired
    private transient UserRepository userRepository;

    public boolean isInject() {
        return userRepository != null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", addressId=" + addressId +
            ", userRepository=" + userRepository +
            '}';
    }
}
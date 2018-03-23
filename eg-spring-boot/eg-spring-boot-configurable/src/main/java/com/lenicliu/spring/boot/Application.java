package com.lenicliu.spring.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lenicliu
 */
@Controller
@SpringBootApplication
@EnableSpringConfigured
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping("/users/{id}")
    public User users(@PathVariable Long id) {
        User foundUser = userRepository.getById(id);
        logger.info(foundUser.toString());
        return foundUser;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
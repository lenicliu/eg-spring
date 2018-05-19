package com.lenicliu.spring.boot;

import com.lenicliu.spring.boot.domain.Log;
import com.lenicliu.spring.boot.jpa.repo.LogRepository;
import com.lenicliu.spring.boot.jpa.repo.UserRepository;
import com.lenicliu.spring.boot.mybatis.mapper.LogMapper;
import com.lenicliu.spring.boot.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LogRepository logRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(userMapper.find(1L));
        System.out.println(logMapper.list(1L));
        System.out.println(userRepository.findById(1L).get());
        System.out.println(logRepository.findAll(Example.of(Log.builder().uid(1L).build())));
        //error, lazy init
        //System.out.println(userRepository.getOne(1L));
    }
}
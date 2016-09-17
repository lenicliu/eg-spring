package com.lenicliu.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Resource Server
 * Created by lenicliu on 16/9/13.
 */
@RestController
@EnableResourceServer
@SpringBootApplication
public class Application extends ResourceServerConfigurerAdapter {

    @GetMapping("/persons")
    public List<Person> persons() {
        return Arrays.asList(//
                new Person(1, "lenic"),//
                new Person(2, "richard"),//
                new Person(3, "seven")//
        );
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/persons").authorizeRequests().anyRequest().authenticated();
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public static class Person {
        private Integer id;
        private String name;

        public Person(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
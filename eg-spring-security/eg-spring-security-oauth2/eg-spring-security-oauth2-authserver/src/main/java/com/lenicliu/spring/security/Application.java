package com.lenicliu.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * <pre>
 *     $curl localhost:8080/oauth/token -d "grant_type=password&scope=oauth2&username=u5er&password=pa55w0rd" -u cl1ent:5ecret
 *     {"access_token":"44f8b1ea-6f5c-46f4-b99d-8f2aca025b49","token_type":"bearer","refresh_token":"2c86bd49-0bd3-4cfc-8b92-6cda1a37562a","expires_in":43149,"scope":"oauth2"}
 *     curl -H "Authorization: bearer 44f8b1ea-6f5c-46f4-b99d-8f2aca025b49" localhost:8081/persons
 *     [{"id":1,"name":"lenic"},{"id":2,"name":"richard"},{"id":3,"name":"seven"}]
 * </pre>
 * <p>
 * Auth Server
 * Created by lenicliu on 16/9/17.
 */
@RestController
@EnableResourceServer
@SpringBootApplication
@EnableAuthorizationServer
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RestController
    protected static class PrincipalController {
        @GetMapping("/principal")
        public Principal principal(Principal principal) {
            return principal;
        }
    }
}
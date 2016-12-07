package com.lenicliu.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("serverinternalerror")
    public String serverinternalerror() throws Exception {
        throw new Exception("error");
    }
}
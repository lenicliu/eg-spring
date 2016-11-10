package com.lenicliu.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by lenicliu on 11/10/16.
 */
@Controller
public class WebController {
    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping(value = {"/metrics"})
    public String metrics() {
        return "metrics";
    }
}

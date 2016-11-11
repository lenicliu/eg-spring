package com.lenicliu.blog.controller;

import com.lenicliu.blog.model.Option;
import com.lenicliu.blog.query.NoteQuery;
import com.lenicliu.blog.service.NoteService;
import com.lenicliu.blog.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by lenicliu on 11/10/16.
 */
@Controller
public class WebController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private OptionService optionService;

    @GetMapping(value = {"/", "/index"})
    public String index(Map<String, Object> model, @RequestParam(name = "page", defaultValue = "1") int page) {
        Option option = optionService.find(OptionService.PAGESIZE);
        model.put("notes", noteService.find(new NoteQuery(page, option.getInt())));
        return "index";
    }

    @GetMapping(value = {"/metrics"})
    public String metrics() {
        return "metrics";
    }
}

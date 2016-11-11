package com.lenicliu.blog.service.impl;

import com.lenicliu.blog.mapper.OptionMapper;
import com.lenicliu.blog.model.Option;
import com.lenicliu.blog.service.OptionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenicliu on 11/10/16.
 */
@Service
@Transactional
public class OptionServiceImpl implements OptionService {
    @Autowired
    private OptionMapper optionMapper;
    private static final Map<String, String> DEFAULT = new HashMap<String, String>() {{
        put(OptionService.PAGESIZE, "5");
    }};

    @Override
    public Option find(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        Option option = optionMapper.find(name);
        if (option == null && DEFAULT.get(name) != null) {
            optionMapper.insert(name, DEFAULT.get(name));
            option = optionMapper.find(name);
        }
        return option == null ? new Option(name, DEFAULT.get(name)) : option;
    }
}

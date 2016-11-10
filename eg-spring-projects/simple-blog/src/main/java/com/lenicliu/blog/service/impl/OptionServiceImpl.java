package com.lenicliu.blog.service.impl;

import com.lenicliu.blog.mapper.OptionMapper;
import com.lenicliu.blog.model.Option;
import com.lenicliu.blog.service.OptionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lenicliu on 11/10/16.
 */
@Service
@Transactional
public class OptionServiceImpl implements OptionService {
    @Autowired
    private OptionMapper optionMapper;
    @Override
    public Option find(String name) {
        if(StringUtils.isBlank(name)){
            return null;
        }
        return optionMapper.find(name);
    }
}

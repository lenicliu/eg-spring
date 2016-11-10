package com.lenicliu.blog.service.impl;

import com.lenicliu.blog.model.Option;
import com.lenicliu.blog.model.User;
import com.lenicliu.blog.service.OptionService;
import com.lenicliu.blog.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lenicliu on 11/10/16.
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private static final String UNKNOWN = "unknown";
    @Autowired
    private OptionService optionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        username = StringUtils.trimToEmpty(username);
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException(UNKNOWN);
        }
        Option option = optionService.find(OptionService.USERNAME);
        if (option == null || !StringUtils.equals(username, option.getValue())) {
            throw new UsernameNotFoundException(username);
        }
        option = optionService.find(OptionService.PASSWORD);
        return new User(username, option == null ? null : option.getValue());
    }
}

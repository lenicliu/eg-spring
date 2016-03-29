package com.lenicliu.spring.boot;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private Logger				logger			= LoggerFactory.getLogger(getClass());

	private Map<Long, String>	userRepository	= new LinkedHashMap<Long, String>();

	public UserServiceImpl() {
		userRepository.put(new Long(1), "LenicLiu");
	}

	@Override
	public String find(Long id) {
		logger.info("find User [" + id + "]");
		if (id == null) {
			return null;
		}
		return userRepository.get(id);
	}
}
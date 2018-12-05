package com.atomikos.service.test.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atomikos.entity.test.User;
import com.atomikos.mapper.test.UserMapper;
import com.atomikos.service.test.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserMapper userMapper;

	public void insert(User user){
		userMapper.insert(user);
	}
}
package com.demo.springboot2.c5database.beetl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.springboot2.c5database.beetl.dao.UserDao;
import com.demo.springboot2.domain.User;

@Service
public class UserService {

	@Autowired
	private CreditSystemService creditSystemService;
	@Autowired
	private UserDao userDao;

	public int getCredit(int id){
		User u = userDao.single(id);
		if(u != null){
			return creditSystemService.getUserCredit(u.getId());
		}else{
			return -1;
		}
	}

}

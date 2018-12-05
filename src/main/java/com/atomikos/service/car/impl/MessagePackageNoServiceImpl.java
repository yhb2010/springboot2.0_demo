package com.atomikos.service.car.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atomikos.entity.car.MessagePackageNo;
import com.atomikos.mapper.car.MessagePackageNoMapper;
import com.atomikos.service.car.MessagePackageNoService;

@Service
public class MessagePackageNoServiceImpl implements MessagePackageNoService {
	@Autowired
	private MessagePackageNoMapper messagePackageNoMapper;

	public void insert(MessagePackageNo messagePackageNo){
		messagePackageNoMapper.insert(messagePackageNo);
	}
}
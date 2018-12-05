package com.atomikos.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atomikos.entity.car.MessagePackageNo;
import com.atomikos.entity.test.User;
import com.atomikos.service.group.IndexService;

@RestController
public class TestController {

	@Autowired
    private IndexService indexService; // 可以测试分布式事务

	@GetMapping("/ato")
	public void addUser1AndUser2() {
		// 构造数据
        MessagePackageNo messagePackageNo = new MessagePackageNo();
        messagePackageNo.setNo(99);
        messagePackageNo.setCreateTime(new Date());

        User user = new User();
        user.setAge(25);
        user.setName("lcj");
        user.setCreateTime(new Date());

        indexService.save(messagePackageNo,user);
	}

}

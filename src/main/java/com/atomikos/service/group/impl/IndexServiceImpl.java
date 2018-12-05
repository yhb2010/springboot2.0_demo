package com.atomikos.service.group.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atomikos.entity.car.MessagePackageNo;
import com.atomikos.entity.test.User;
import com.atomikos.service.car.MessagePackageNoService;
import com.atomikos.service.group.IndexService;
import com.atomikos.service.test.UserService;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private MessagePackageNoService messagePackageNoService;

    @Autowired
    private UserService userService;

    /**
     * 保存数据
     *
     * @param messagePackageNo
     * @param user
     */
    @Override
    @Transactional
    public void save(MessagePackageNo messagePackageNo, User user) {
        messagePackageNoService.insert(messagePackageNo);
        userService.insert(user);
        int i = 4 / 0; // 除0异常,测试事务
    }

}
package com.atomikos.service.group;

import com.atomikos.entity.car.MessagePackageNo;
import com.atomikos.entity.test.User;

public interface IndexService {

    /**
     * 保存数据
     *
     * @param messagePackageNo
     * @param user
     */
    public void save(MessagePackageNo messagePackageNo, User user);

}
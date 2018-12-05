package com.atomikos.mapper.car;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.atomikos.entity.car.MessagePackageNo;

@Repository
public interface MessagePackageNoMapper {

    /**
     * 保存
     * @param no
     */
    @Insert("INSERT INTO tb_message_package_no (no, create_time) VALUES (#{no.no}, NOW())")
    void insert(@Param("no") MessagePackageNo no);

}
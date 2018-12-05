package com.atomikos.mapper.test;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.atomikos.entity.test.User;

@Repository
public interface UserMapper {

    @Insert("INSERT INTO tb_user (id, name, age, create_time) VALUES (NULL, #{user.name}, #{user.age}, NOW())")
    void insert(@Param("user") User user);

}
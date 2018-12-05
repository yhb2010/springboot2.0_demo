package com.demo2.springboot2.c11mongo.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.demo2.springboot2.c11mongo.domain.User;

public interface UserRepository extends MongoRepository<User, Integer> {

	public User queryByAge(int age);

	public List<User> findByAgeGreaterThan(int age);

	public List<User> findByAgeGreaterThan(int age, Pageable pageable);

	//若想指定返回的键，我们需要在PersonRepository中添加方法，同时使用注解@Query。
	//其中value是查询的条件，？0这个是占位符，对应着方法中参数中的第一个参数，如果对应的是第二个参数则为？1。fields是我们指定的返回字段，其中id是自动返回的，不用我们指定，bson中{'name':1}的1代表true，也就是代表返回的意思。
	@Query(fields="{'name':1}")
    public List<User> findByAge(int age, Pageable pageable);

}

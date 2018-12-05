package com.demo2.springboot2.c11mongo;

import static org.springframework.data.mongodb.core.query.Criteria.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo2.springboot2.c11mongo.dao.UserRepository;
import com.demo2.springboot2.c11mongo.domain.Address;
import com.demo2.springboot2.c11mongo.domain.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@RestController
public class MongoController2 {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/mongo/rep/findall")
	public List<User> queryAll() throws Exception {
        return userRepository.findAll();
    }

	@GetMapping("/mongo/rep/page/{page}/{rows}")
	public Page<User> queryAllByPage(@PathVariable int page, @PathVariable int rows) throws Exception {
		//第一个是页码，第二个是页面大小。注意：页码在mongodb中是从0开始的。
        PageRequest pageRequest = PageRequest.of(page-1, rows);
        return userRepository.findAll(pageRequest);
    }

	@GetMapping("/mongo/rep/count")
	public int count() throws Exception {
        long size = userRepository.count();
        int count = Integer.valueOf(String.valueOf(size));
        return count;
    }

	//find + By + 属性名（首字母大写）
	@GetMapping("/mongo/rep/age/{age}")
	public User queryByAge(@PathVariable int age) throws Exception {
        return userRepository.queryByAge(age);
    }

	@GetMapping("/mongo/rep/age2/{age}")
	public List<User> findByAgeGreaterThan(@PathVariable int age) throws Exception {
		return userRepository.findByAgeGreaterThan(age);
	}

	@GetMapping("/mongo/rep/age/page/{age}/{page}/{rows}")
	public List<User> findByAgeGreaterThan(@PathVariable int age, @PathVariable int page, @PathVariable int rows) throws Exception {
		PageRequest pageRequest = PageRequest.of(page-1, rows);
		return userRepository.findByAgeGreaterThan(age, pageRequest);
	}

	@GetMapping("/mongo/rep/age/{age}/{page}/{rows}")
	public List<User> findByAgeGreaterThan2(@PathVariable int age, @PathVariable int page, @PathVariable int rows) throws Exception {
		PageRequest pageRequest = PageRequest.of(page-1, rows);
		return userRepository.findByAge(age, pageRequest);
	}

}

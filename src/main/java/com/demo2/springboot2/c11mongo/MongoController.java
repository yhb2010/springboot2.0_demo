package com.demo2.springboot2.c11mongo;

import static org.springframework.data.mongodb.core.query.Criteria.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo2.springboot2.c11mongo.domain.Address;
import com.demo2.springboot2.c11mongo.domain.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@RestController
public class MongoController {

	@Autowired
	private MongoTemplate mongoTemplate;

	@GetMapping("/mongo/user")
	public User add(){
		User u = new User(3, "王五", 21, new Address(1, "东直门"));
		//insert添加文档
		//save添加或更新文档
		mongoTemplate.insert(u);
		return u;
	}

	@GetMapping("/mongo/user/{id}")
	public User find(@PathVariable int id){
		return mongoTemplate.findById(id, User.class);
	}

	@GetMapping("/mongo/user/find")
	public List<User> find(){
		Query query = new Query(where("address.area").is("东直门").andOperator(where("age").is(22)));
		return mongoTemplate.find(query, User.class);
	}

	@GetMapping("/mongo/user/{pageNum}/{pageSize}")
	public List<User> find(@PathVariable int pageNum, @PathVariable int pageSize){
		Query query = new Query(where("age").gt(20));
		long count = mongoTemplate.count(query, User.class);
		long totalPage = count%pageSize == 0 ? count/pageSize : count/pageSize+1;
		int skip = (pageNum-1)*pageSize;
		query.skip(skip).limit(pageSize);
		return mongoTemplate.find(query, User.class);
	}

	@GetMapping("/mongo/user/update")
	public long update(){
		Query query = new Query(where("age").is(22));
		Update update = new Update();
		update.inc("age", 1);
		//updateFirst更新第一条，updateMulti更新所有
		UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
		return result.getModifiedCount();
	}

	@GetMapping("/mongo/user/delete")
	public long delete(){
		User u= new User();
		u.setId(1);
		DeleteResult result = mongoTemplate.remove(u);
		return result.getDeletedCount();
	}

}

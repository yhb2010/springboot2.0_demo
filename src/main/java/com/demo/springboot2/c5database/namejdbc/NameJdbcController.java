package com.demo.springboot2.c5database.namejdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot2.c5database.namejdbc.dao.NameChapterDao;

@RestController
public class NameJdbcController {

	@Autowired
	private NameChapterDao nameChapterDao;

	@GetMapping("/count2/{chapterListID}")
	public int queryCount(@PathVariable int chapterListID){
		return nameChapterDao.queryCount(chapterListID);
	}

	@GetMapping("/count22/{chapterListID}")
	public int queryCount2(@PathVariable int chapterListID){
		return nameChapterDao.queryCount2(chapterListID);
	}

}

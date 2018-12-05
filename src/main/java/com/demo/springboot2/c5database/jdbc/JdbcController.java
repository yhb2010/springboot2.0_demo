package com.demo.springboot2.c5database.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot2.c5database.jdbc.dao.ChapterDao;
import com.demo.springboot2.domain.Chapter;

@RestController
public class JdbcController {

	@Autowired
	private ChapterDao chapterDao;

	@GetMapping("/count")
	public int queryCount(){
		return chapterDao.queryCount();
	}

	@GetMapping("/count/{chapterListID}")
	public int queryCount(@PathVariable int chapterListID){
		return chapterDao.queryCount(chapterListID);
	}

	@GetMapping("/chapter/{chapterID}")
	public Chapter getChapter(@PathVariable int chapterID){
		return chapterDao.getChapter(chapterID);
	}

	@GetMapping("/chapters/{chapterListID}")
	public List<Chapter> getChapters(@PathVariable int chapterListID){
		return chapterDao.getChapters(chapterListID);
	}

	@GetMapping("/chapter/update/{chapterID}")
	public int update(@PathVariable int chapterID){
		return chapterDao.update(chapterID);
	}

}

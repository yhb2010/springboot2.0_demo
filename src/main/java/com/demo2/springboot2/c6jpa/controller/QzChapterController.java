package com.demo2.springboot2.c6jpa.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo2.springboot2.c6jpa.domain.QzChapter;
import com.demo2.springboot2.c6jpa.domain.Qz_Chapter;
import com.demo2.springboot2.c6jpa.service.QzChapterEntityManagerService;
import com.demo2.springboot2.c6jpa.service.QzChapterService;

@RestController
public class QzChapterController {

	@Autowired
	private QzChapterService qzChapterService;
	@Autowired
	private QzChapterEntityManagerService qzChapterEntityManagerService;

	@GetMapping("/jpa/add")
	public Integer addChapter(){
		return qzChapterService.addChapter(new QzChapter(1, "测试", 1, (byte)1, (short)1, (short)1, (byte)1, "1", new Date(), "1", new Date()));
	}

	@GetMapping("/jpa/findAll")
	public List<QzChapter> findAll(){
		return qzChapterService.findAll();
	}

	@GetMapping("/jpa/page")
	public List<QzChapter> findPage(){
		return qzChapterService.findPage();
	}

	@GetMapping("/jpa/chapter")
	public Qz_Chapter findChapter(){
		return qzChapterService.findChapter(3004);
	}

	@GetMapping("/jpa/chapter2")
	public Qz_Chapter findChapter2(){
		return qzChapterService.findChapter2(3005);
	}

	@GetMapping("/jpa/chapter3")
	public List<Qz_Chapter> findChapter3(){
		return qzChapterService.findChapter3(272);
	}

	@GetMapping("/jpa/chapter4")
	@Transactional
	public int findChapter4(){
		return qzChapterService.update("测试1", 3004);
	}

	@GetMapping("/jpa/chapter5")
	@Transactional
	public List<Qz_Chapter> findChapter5(){
		PageRequest request = PageRequest.of(1, 3, Direction.DESC, "chapterID");
		Page<Qz_Chapter> page = qzChapterEntityManagerService.queryChapter(272, request);
		return page.getContent();
	}

}

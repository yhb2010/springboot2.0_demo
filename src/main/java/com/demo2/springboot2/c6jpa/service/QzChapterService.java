package com.demo2.springboot2.c6jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.demo2.springboot2.c6jpa.dao.QzChapterRepository;
import com.demo2.springboot2.c6jpa.dao.QzChapterRepository2;
import com.demo2.springboot2.c6jpa.domain.QzChapter;
import com.demo2.springboot2.c6jpa.domain.Qz_Chapter;

@Service
public class QzChapterService {

	@Autowired
	private QzChapterRepository qzChapterRepository;
	@Autowired
	private QzChapterRepository2 qzChapterRepository2;

	public Integer addChapter(QzChapter qzChapter){
		qzChapterRepository.save(qzChapter);
		Integer chapterID = qzChapter.getChapterID();
		qzChapter.setChapterName("测试1");
		//第二次save如果主键存在就更新
		qzChapterRepository.save(qzChapter);
		return chapterID;
	}

	//排序
	public List<QzChapter> findAll(){
		Sort sort = new Sort(Direction.DESC, "chapterID");
		// Sort sort2 = new Sort(Order.desc("id"));
		return qzChapterRepository.findAll(sort);
	}

	//分页
	public List<QzChapter> findPage(){
		//page：总是从0开始，表示查询页，size每页期望行数
		PageRequest request = PageRequest.of(1, 10, Direction.DESC, "chapterID");
		Page<QzChapter> page = qzChapterRepository.findAll(request);
		System.out.println(page.getTotalPages());
		System.out.println(page.getTotalElements());
		return page.getContent();
	}

	//使用@Query
	public Qz_Chapter findChapter(Integer chapterID){
		return qzChapterRepository2.findChapter(chapterID);
	}

	//使用@Query，使用原始sql
	public Qz_Chapter findChapter2(Integer chapterID){
		return qzChapterRepository2.findChapter2(chapterID);
	}

	//使用@Query，分页
	public List<Qz_Chapter> findChapter3(Integer chapterListID){
		PageRequest request = PageRequest.of(1, 3, Direction.DESC, "chapterID");
		Page<Qz_Chapter> page = qzChapterRepository2.findChapter3(chapterListID, request);
		return page.getContent();
	}

	//更新、删除，需要@Modifying
	public int update(String chapterName, Integer chapterID){
		return qzChapterRepository2.update(chapterName, chapterID);
	}

}

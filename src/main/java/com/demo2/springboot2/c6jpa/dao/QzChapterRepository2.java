package com.demo2.springboot2.c6jpa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.demo2.springboot2.c6jpa.domain.Qz_Chapter;

public interface QzChapterRepository2 extends JpaRepository<Qz_Chapter, Integer>{

	@Query(value="select c from Qz_Chapter c where c.chapterID=?1")
	public Qz_Chapter findChapter(Integer chapterID);

	//也都支持命名参数:chapterID
	//如果sql返回的结果集不是entity，可以用Object[]数组代替，例如List<Object[]>
	@Query(value="select * from Qz_Chapter where chapterID=?1", nativeQuery = true)
	public Qz_Chapter findChapter2(Integer chapterID);

	@Query(value="select * from Qz_Chapter where chapterListID=?1", nativeQuery = true)
	public Page<Qz_Chapter> findChapter3(Integer chapterListID, Pageable page);

	@Modifying
	@Query(value="update Qz_Chapter c set c.chapterName=?1 where c.chapterID=?2")
	public int update(String chapterName, Integer chapterID);

}

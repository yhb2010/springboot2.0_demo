package com.demo.springboot2.c5database.beetl.dao;

import org.beetl.sql.core.mapper.BaseMapper;

import com.demo.springboot2.domain.Qz_Chapterlist;

public interface QzChapterlistDao extends BaseMapper<Qz_Chapterlist> {

	Qz_Chapterlist selectChapterList(Qz_Chapterlist list);

}

package com.demo.springboot2.c5database.beetl.dao;

import org.beetl.sql.core.mapper.BaseMapper;
import com.demo.springboot2.domain.Qz_Chapterlist_Search;

public interface QzChapterlistSearchDao extends BaseMapper<Qz_Chapterlist_Search> {

	Qz_Chapterlist_Search selectSearch(Qz_Chapterlist_Search search);

}

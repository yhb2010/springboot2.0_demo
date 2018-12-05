package com.demo.springboot2.c5database.namejdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.demo.springboot2.domain.Chapter;

@Repository
public class NameChapterDao {

	//对jdbcTemplate的增强，可以通过参数名字作为占位符
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int queryCount(int chapterListID){
		String sql = "select count(*) from qz_chapter where chapterListID=:chapterListID";
		MapSqlParameterSource namedParam = new MapSqlParameterSource();
		namedParam.addValue("chapterListID", chapterListID);
		return namedParameterJdbcTemplate.queryForObject(sql, namedParam, Integer.class);
	}

	//还可以直接通过java bean的方式
	public int queryCount2(int chapterListID){
		Chapter c = new Chapter();
		c.setChapterListID(chapterListID);
		String sql = "select count(*) from qz_chapter where chapterListID=:chapterListID";
		SqlParameterSource source = new BeanPropertySqlParameterSource(c);
		return namedParameterJdbcTemplate.queryForObject(sql, source, Integer.class);
	}

}

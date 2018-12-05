package com.demo.springboot2.c5database.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.demo.springboot2.domain.Chapter;

@Repository
public class ChapterDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int queryCount(){
		int count = jdbcTemplate.queryForObject("select count(*) from qz_chapter", Integer.class);
		return count;
	}

	public int queryCount(int chapterListID){
		int count = jdbcTemplate.queryForObject("select count(*) from qz_chapter where chapterListID=?", Integer.class, chapterListID);
		return count;
	}

	public Chapter getChapter(int chapterID){
		String sql = "select * from qz_chapter where chapterID=?";
		Chapter c = jdbcTemplate.queryForObject(sql, new ChapterRowMapper(), chapterID);
		return c;
	}

	public List<Chapter> getChapters(int chapterListID){
		String sql = "select * from qz_chapter where chapterListID=?";
		List<Chapter> cl = jdbcTemplate.query(sql, new ChapterRowMapper(), chapterListID);
		return cl;
	}

	static class ChapterRowMapper implements RowMapper<Chapter>{

		@Override
		public Chapter mapRow(ResultSet rs, int rowNum) throws SQLException {
			Chapter c = new Chapter();
			c.setChapterID(rs.getInt("chapterID"));
			c.setChapterName(rs.getString("chapterName"));
			return c;
		}

	}

	public int update(int chapterID) {
		String sql = "update qz_chapter set chapterName=? where chapterID=?";
		return jdbcTemplate.update(sql, "综合1", chapterID);
	}

}

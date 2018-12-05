package com.demo.springboot2.domain;

import org.beetl.sql.core.annotatoin.AssignID;

/**
 * 章节
 * @author sangshan
 *
 */
public class Qz_Chapterlist extends BaseDomain {

	private static final long serialVersionUID = 8729092033075810560L;
	/**
	 * 章节id
	 * SeqId 用于oralce
    AutoId,用于自增。这是主键默认设置
    AssignId，代码指定主键
	 */
	@AssignID
	private Integer chapterListID;
	/**
	 * 章节名称
	 */
	private String chapterListName;

	public Integer getChapterListID() {
		return chapterListID;
	}
	public void setChapterListID(Integer chapterListID) {
		this.chapterListID = chapterListID;
	}
	public String getChapterListName() {
		return chapterListName;
	}
	public void setChapterListName(String chapterListName) {
		this.chapterListName = chapterListName;
	}



}

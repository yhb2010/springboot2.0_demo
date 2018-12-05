package com.demo.springboot2.domain;

import org.beetl.sql.core.annotatoin.AssignID;

/**
 * 章节
 * @author sangshan
 *
 */
public class Qz_Chapterlist_Search extends BaseDomain {

	private static final long serialVersionUID = 8729092033075810560L;
	/**
	 * 章节id
	 * SeqId 用于oralce
    AutoId,用于自增。这是主键默认设置
    AssignId，代码指定主键
	 */
	@AssignID
	private Integer searchID;
	/**
	 * 章节名称
	 */
	private String bizKey;
	private Integer chapterListID;
	public Integer getSearchID() {
		return searchID;
	}
	public void setSearchID(Integer searchID) {
		this.searchID = searchID;
	}
	public String getBizKey() {
		return bizKey;
	}
	public void setBizKey(String bizKey) {
		this.bizKey = bizKey;
	}
	public Integer getChapterListID() {
		return chapterListID;
	}
	public void setChapterListID(Integer chapterListID) {
		this.chapterListID = chapterListID;
	}




}

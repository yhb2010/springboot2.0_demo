package com.demo2.springboot2.c6jpa.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "qz_chapterlist")
public class QzChapterlist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer chapterListID;
	@Column
	private String chapterListName;
	@Column
	private Short statVer;
	@Column
	private Byte status;
	@Column
	private String creator;
	@Column
	private Date createTime;
	@Column
	private String operator;
	@Column
	private Date operateTime;

	public Short getStatVer() {
		return statVer;
	}
	public void setStatVer(Short statVer) {
		this.statVer = statVer;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
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

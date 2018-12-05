package com.demo2.springboot2.c6jpa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**还可以简化，把@ManyToOne、@OneToMany属性都取消
 * @author DELL
 *
 */
@Entity(name = "qz_chapter")
public class QzChapter {
	@Id
	private Integer chapterID;
	@Column
	private String chapterName;
	@Column
	private Integer chapterListID;
	@Column
	private Byte showStatus;
	@Column
	private Short sequence;
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

	public Integer getChapterID() {
		return chapterID;
	}

	public void setChapterID(Integer chapterID) {
		this.chapterID = chapterID;
	}

	public Integer getChapterListID() {
		return chapterListID;
	}

	public void setChapterListID(Integer chapterListID) {
		this.chapterListID = chapterListID;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public Byte getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Byte showStatus) {
		this.showStatus = showStatus;
	}

	public Short getSequence() {
		return sequence;
	}

	public void setSequence(Short sequence) {
		this.sequence = sequence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chapterID == null) ? 0 : chapterID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QzChapter other = (QzChapter) obj;
		if (chapterID == null) {
			if (other.chapterID != null)
				return false;
		} else if (!chapterID.equals(other.chapterID))
			return false;
		return true;
	}

	public QzChapter(Integer chapterID, String chapterName,
			Integer chapterListID, Byte showStatus, Short sequence,
			Short statVer, Byte status, String creator, Date createTime,
			String operator, Date operateTime) {
		super();
		this.chapterID = chapterID;
		this.chapterName = chapterName;
		this.chapterListID = chapterListID;
		this.showStatus = showStatus;
		this.sequence = sequence;
		this.statVer = statVer;
		this.status = status;
		this.creator = creator;
		this.createTime = createTime;
		this.operator = operator;
		this.operateTime = operateTime;
	}

	public QzChapter() {
		super();
	}

}

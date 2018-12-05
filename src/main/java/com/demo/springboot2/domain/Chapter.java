package com.demo.springboot2.domain;

/**
 * 章节
 * @author sangshan
 *
 */
public class Chapter {

	private static final long serialVersionUID = 8729092033075810560L;
	/**
	 * 章节id
	 */
	private Integer chapterID;
	/**
	 * 章节名称
	 */
	private String chapterName;
	/**
	 * 章节策略id
	 */
	private Integer chapterListID;
	/**
	 * 是否显示
	 */
	private Byte showStatus;
	/**
	 * 排序值
	 */
	private Short sequence;

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
		Chapter other = (Chapter) obj;
		if (chapterID == null) {
			if (other.chapterID != null)
				return false;
		} else if (!chapterID.equals(other.chapterID))
			return false;
		return true;
	}

}

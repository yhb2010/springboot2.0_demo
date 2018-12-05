package com.demo.springboot2.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.beetl.sql.core.TailBean;


/**基础实体类
 * @author DELL
 *
 * @param <T>
 */
@SuppressWarnings("serial")
//pojo继承TailBean类后，sql查询结果集映射不到的字段将会放到此类中，称之为混合模型，通过get(XXX)取值
public class BaseDomain extends TailBean implements Serializable {

	private Short statVer;

	private Byte status;

	private String creator;

	private Date createTime;

	private String operator;

	private Date operateTime;

	private transient Integer queryKey;//当查询结果返回map时，用于存储map的key

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
	public void setQueryKey(Integer queryKey) {
		this.queryKey = queryKey;
	}
	public Integer getQueryKey() {
		return queryKey;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	public BaseDomain(Short statVer, Byte status, String creator,Date createTime, String operator, Date operateTime) {
		super();
		this.statVer = statVer;
		this.status = status;
		this.creator = creator;
		this.createTime = createTime;
		this.operator = operator;
		this.operateTime = operateTime;
	}
	public BaseDomain(String creator, Date createTime, String operator, Date operateTime) {
		super();
		this.creator = creator;
		this.createTime = createTime;
		this.operator = operator;
		this.operateTime = operateTime;
	}
	public BaseDomain(String creator, String operator) {
		super();
		this.creator = creator;
		this.operator = operator;
	}
	public BaseDomain() {
		super();
	}

}
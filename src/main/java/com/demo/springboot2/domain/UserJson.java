package com.demo.springboot2.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@SuppressWarnings("serial")
public class UserJson implements Serializable{

	public interface IdView{};
	public interface IdNameView extends IdView{};

	@JsonView(IdView.class) //类似于验证里面的group
	private Integer id;
	@JsonView(IdNameView.class)
	@JsonProperty("userName")//起别名
	private String name;
	@JsonIgnore //忽略
	//@JsonIgnoreProperties({"id", "name"}) //忽略一组
	private Integer age;
	@JsonIgnore //忽略
	private Map map = new HashMap();
	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	private Date createTime;

	@JsonAnySetter //所有未找到对应属性的都调用此方法
	private void other(String property, Object value){
		map.put(property, value);
	}
	@JsonAnyGetter //取出map中的每一个值进行序列化
	public Map getOtherProperties(){
		return map;
	}
	public UserJson(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}

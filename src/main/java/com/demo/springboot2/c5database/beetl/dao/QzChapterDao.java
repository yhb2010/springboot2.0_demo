package com.demo.springboot2.c5database.beetl.dao;

import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.annotatoin.SqlStatementType;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import com.demo.springboot2.domain.Qz_Chapter;

//BaseMapper里面内置了很多方法
//insert：插入实体对象到数据库
//insertTemplate：插入实体对象到数据库，只插入非null属性
//insertBatch：批量插入实体
//updateById：按照主键更新实体
//updateTemplateById：按照主键更新实体，只更新非null属性
//deleteById：按照主键删除实体，一些大型应用严格来说禁止删除，
//unique：根据主键查询实体，如果没有找到抛出Runtime异常
//single：根据主键查询实体，如果没有找到，返回null
//lock：根据主键使用数据库悲观锁，相当于select * from table where id=? for update
//all：返回所有实体
//allCount：所有实体个数
//template：查询符合模板的所有实体
//templateOne：查询符合模板的第一个实体
//execute：执行一个jdbc查询，在java中提供一个sql语句
//executeUpdate：执行一个jdbc更新操作，在java中提供一个sql语句
public interface QzChapterDao extends BaseMapper<Qz_Chapter> {

	//参数可以使用@Param注解，map或者对象，还可以混用
	//默认查询方法以select开头，同理还有update、insert、delete，如果不以这些关键字开头，需要加@SqlStatement注解
	//@SqlStatement(type=SqlStatementType.INSERT)
	public Qz_Chapter selectByChapterListID(Qz_Chapter c);

	public PageQuery<Qz_Chapter> queryForPage(PageQuery query);

}

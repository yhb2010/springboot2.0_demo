package com.demo2.springboot2.c6jpa.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.demo2.springboot2.c6jpa.domain.Qz_Chapter;

@Service
public class QzChapterEntityManagerService {

	@Autowired
	//EntityManager提供了实体操作的所有接口，可以通过自动注入的方式注入到spring管理的bean中。
	private EntityManager em;

	public Page<Qz_Chapter> queryChapter(Integer chapterListID, Pageable page){
		//构造JPQL和实际的参数
		StringBuilder baseJpql = new StringBuilder("from Qz_Chapter c where 1=1 ");
		Map<String, Object> paras = new HashMap<>();
		if(chapterListID != null){
			baseJpql.append("and c.chapterListID=:chapterListID");
			paras.put("chapterListID", chapterListID);
		}
		//查询满足条件的总数
		long count = getQueryCount(baseJpql, paras);
		if(count == 0){
			return new PageImpl<>(Collections.emptyList(), page, 0);
		}
		//查询满足条件的结果集
		List<Qz_Chapter> list = getQueryResult(baseJpql, paras, page);
		//返回结果
		Page<Qz_Chapter> ret = new PageImpl<>(list, page, count);
		return ret;
	}

	private List<Qz_Chapter> getQueryResult(StringBuilder baseJpql, Map<String, Object> paras, Pageable page) {
		Query query = em.createQuery("select c " + baseJpql.toString());
		setQueryParam(query, paras);
		query.setFirstResult(page.getPageNumber());// 查询起始位置
		query.setMaxResults((int)page.getOffset());//查询期望返回的总数
		List<Qz_Chapter> list = query.getResultList();
		return list;
	}

	private long getQueryCount(StringBuilder baseJpql, Map<String, Object> paras) {
		Query query = em.createQuery("select count(1) " + baseJpql.toString());
		setQueryParam(query, paras);
		Number num = (Number)query.getSingleResult();
		return num.longValue();
	}

	private void setQueryParam(Query query, Map<String, Object> paras) {
		for (Map.Entry<String,Object> entry : paras.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}

}

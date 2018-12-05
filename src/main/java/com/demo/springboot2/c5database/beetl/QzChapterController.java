package com.demo.springboot2.c5database.beetl;

import java.util.Date;
import java.util.List;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot2.c5database.beetl.dao.QzChapterDao;
import com.demo.springboot2.c5database.beetl.dao.QzChapterlistDao;
import com.demo.springboot2.c5database.beetl.dao.QzChapterlistSearchDao;
import com.demo.springboot2.domain.Qz_Chapter;
import com.demo.springboot2.domain.Qz_Chapterlist;
import com.demo.springboot2.domain.Qz_Chapterlist_Search;

@RestController
public class QzChapterController {

	@Autowired
	private SQLManager sqlManager;
	//可以直接用sqlManager，也可以通过继承BaseMapper，注入Dao
	@Autowired
	private QzChapterDao qzChapterDao;
	@Autowired
	private QzChapterlistSearchDao qzChapterlistSearchDao;
	@Autowired
	private QzChapterlistDao qzChapterlistDao;

	//single 触发查询，返回一个对象，如果没有，返回null
	//unique 触发查询，返回一个对象，如果没有，或者有多个，抛出异常
	@GetMapping("/qzchapter/{chapterID}")
	public Qz_Chapter getChapter(@PathVariable int chapterID){
		return sqlManager.unique(Qz_Chapter.class, chapterID);
	}

	@GetMapping("/qzchaptersearch/{searchID}")
	public Qz_Chapterlist_Search getChapterSearch(@PathVariable int searchID){
		Qz_Chapterlist_Search search = new Qz_Chapterlist_Search();
		search.setSearchID(searchID);
		return qzChapterlistSearchDao.selectSearch(search);
	}

	@GetMapping("/qzchapterlist/{chapterListID}")
	public Qz_Chapterlist getChapterList(@PathVariable int chapterListID){
		Qz_Chapterlist list = new Qz_Chapterlist();
		list.setChapterListID(chapterListID);
		return qzChapterlistDao.selectChapterList(list);
	}

	@GetMapping("/qzchapter/save")
	public String save(){
		Qz_Chapter c = new Qz_Chapter();
		//必须是自增主键才行
		c.setChapterID(3003);
		c.setChapterName("测试");
		c.setChapterListID(1);
		c.setShowStatus((byte)1);
		c.setSequence((short)1);
		c.setStatVer((short)1);
		c.setStatus((byte)1);
		c.setCreateTime(new Date());
		c.setCreator("zsl");
		c.setOperateTime(new Date());
		c.setOperator("zsl");
		sqlManager.insert(c);
		return "success";
	}

	//xxxTemplateXxx：该操作会忽略为null值或者为空值的属性
	@GetMapping("/qzchapter/update")
	public String update(){
		Qz_Chapter c = new Qz_Chapter();
		//必须是自增主键才行
		c.setChapterID(3004);
		c.setChapterName("综合1");
		sqlManager.updateTemplateById(c);
		return "success";
	}

	@GetMapping("/qzchapter/cc/{chapterID}")
	public Qz_Chapter get(@PathVariable int chapterID){
		Qz_Chapter c = new Qz_Chapter();
		c.setChapterID(chapterID);
		//第一个参数：sql文件名.语句的唯一标识
		//第二个参数：查询参数
		//第三个参数：结果对应的目标类型，还可以是map
		Qz_Chapter cc = sqlManager.selectSingle("qz_Chapter.selectByChapterListID", c, Qz_Chapter.class);
		return cc;
	}

	@GetMapping("/qzchapter2/cc/{chapterID}")
	public Qz_Chapter get2(@PathVariable int chapterID){
		Qz_Chapter c = new Qz_Chapter();
		c.setChapterID(chapterID);
		Qz_Chapter cc = qzChapterDao.selectByChapterListID(c);
		System.out.println(cc.get("chapterListName"));
		return cc;
	}

	@GetMapping("/qzchapter/page/{num}")
	public List<Qz_Chapter> queryPage(@PathVariable int num){
		Qz_Chapter c = new Qz_Chapter();
		c.setChapterListID(272);
		PageQuery query = new PageQuery();
		query.setParas(c);
		query.setPageSize(3);
		query.setPageNumber(num);
		//不要在sql语句里写排序，因为不同的数据库语法不一样，直接在程序里使用setOrderBy
		query.setOrderBy("chapterID desc");
		PageQuery<Qz_Chapter> result = qzChapterDao.queryForPage(query);
		return result.getList();
	}

}
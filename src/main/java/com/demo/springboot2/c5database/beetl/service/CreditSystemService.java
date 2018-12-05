package com.demo.springboot2.c5database.beetl.service;

import org.springframework.stereotype.Service;

/**积分服务，这是一个未完成的服务类，用于mock测试
 * @author DELL
 *
 */
@Service
public class CreditSystemService {

	/**通过用户id获取积分，可能是个远程调用
	 * @param id
	 * @return
	 */
	public int getUserCredit(int id){
		return 100;
	}

	/**添加积分
	 * @param id
	 * @param score
	 * @return
	 */
	public boolean addCredit(int id, int score){
		return true;
	}

}

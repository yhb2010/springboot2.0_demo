package com.myselftransaction;

import javax.sql.DataSource;

public class UserService {

	private UserAccountDao userAccountDao;
	private UserOrderDao userOrderDao;
	private TransactionManager manager;

	public UserService(DataSource dataSource){
		userAccountDao = new UserAccountDao(dataSource);
		userOrderDao = new UserOrderDao(dataSource);
		manager = new TransactionManager(dataSource);
	}

	public void action(){
		try{
			manager.start();
			userAccountDao.buy();
			String s = null;
			//s.indexOf(1);
			userOrderDao.order();
			manager.close();
		}catch(Exception e){
			e.printStackTrace();
			manager.rollback();
		}
	}

}

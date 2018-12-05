package com.myselftransaction;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class UserOrderDao {

	private DataSource dataSource;

	public UserOrderDao(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public void order() throws SQLException {
		Connection conn = SingleThreadConnectionHolder.getConnection(dataSource);
		//进行业务操作
		System.out.println("当前用户购买线程：" + Thread.currentThread().getName() + ", 使用管道(hashcode)：" + conn.hashCode());
	}

}

package com.myselftransaction;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class TransactionManager {

	private DataSource dataSource;

	public TransactionManager(DataSource dataSource){
		this.dataSource = dataSource;
	}

	private Connection getConnection() throws SQLException {
		return SingleThreadConnectionHolder.getConnection(dataSource);
	}

	//开启事务
	public void start() throws SQLException {
		Connection conn = getConnection();
		conn.setAutoCommit(false);
	}

	//回滚事务
	public void rollback(){
		Connection conn = null;
		try{
			conn = getConnection();
			conn.rollback();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//关闭事务
	public void close() throws SQLException {
		Connection conn = getConnection();
		conn.commit();
		conn.setAutoCommit(true);
		conn.close();
	}

}

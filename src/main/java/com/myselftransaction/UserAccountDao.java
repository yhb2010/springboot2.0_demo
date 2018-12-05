package com.myselftransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class UserAccountDao {

	private DataSource dataSource;

	public UserAccountDao(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public void buy() throws SQLException {
		Connection conn = SingleThreadConnectionHolder.getConnection(dataSource);
		//进行业务操作
		int i = 0;
	    String sql = "insert into user (name, password, age) values(?, ?, ?)";
	    PreparedStatement pstmt;
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setString(1, "张三");
        pstmt.setString(2, "123");
        pstmt.setInt(3, 20);
        i = pstmt.executeUpdate();
        System.out.println(i);
        pstmt.close();
		System.out.println("当前用户购买线程：" + Thread.currentThread().getName() + ", 使用管道(hashcode)：" + conn.hashCode());
	}

}

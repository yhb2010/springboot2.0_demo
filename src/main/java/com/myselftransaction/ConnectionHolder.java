package com.myselftransaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

public class ConnectionHolder {

	private Map<DataSource, Connection> map = new HashMap<>();

	public Connection getConnectionByDataSource(DataSource dataSource) throws SQLException {
		Connection conn = map.get(dataSource);
		if(conn == null || conn.isClosed()){
			conn = dataSource.getConnection();
			map.put(dataSource, conn);
		}
		return conn;
	}

}

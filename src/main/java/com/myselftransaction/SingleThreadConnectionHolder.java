package com.myselftransaction;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

//这里通过ConnectionHolder类来完成这个过程，需要思考的是在多线程下，这显然是存在问题的。为避免多线程问题，难道我们采用线程安全的Map，比如ConcurrentHashMap，其实我们真正的目的是什么？是保证一个线程下，一个事务的多个操作拿到的是一个Connection，显然使用ConcurrentHashMap根本无法保证！
//本来线程不安全的，通过ThreadLocal这么封装一下，立刻就变成了线程的局部变量，不仅仅安全了，还保证了一个线程下面的操作拿到的Connection是同一个对象！这种思想，确实非常巧妙，这也是无锁编程思想的一种方式！
public class SingleThreadConnectionHolder {

	private static ThreadLocal<ConnectionHolder> threadLocal = new ThreadLocal<>();

	private static ConnectionHolder getConnectionHolder(){
		ConnectionHolder connectionHolder = threadLocal.get();
		if(connectionHolder == null){
			connectionHolder = new ConnectionHolder();
			threadLocal.set(connectionHolder);
		}
		return connectionHolder;
	}

	public static Connection getConnection(DataSource dataSource) throws SQLException {
		return getConnectionHolder().getConnectionByDataSource(dataSource);
	}

}

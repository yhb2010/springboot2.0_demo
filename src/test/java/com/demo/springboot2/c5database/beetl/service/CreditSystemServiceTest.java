package com.demo.springboot2.c5database.beetl.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.junit.MockitoJUnitRunner;

//先不用springboot容器测试，直接用mock测试
@RunWith(MockitoJUnitRunner.class)
public class CreditSystemServiceTest {

	@Test
	@Ignore
	public void test() {
		CreditSystemService creditServer = mock(CreditSystemService.class);
		when(creditServer.getUserCredit(anyInt())).thenReturn(1000);
		int ret = creditServer.getUserCredit(10);
		assertEquals(1000, ret);
		//可以mock任何对象：
		List l = mock(LinkedList.class);
	}

	@Test
	@Ignore
	public void test2() {
		CreditSystemService creditServer = mock(CreditSystemService.class);
		int id = 10;
		//一般不推荐使用any，要用具体的数据
		when(creditServer.getUserCredit(eq(id))).thenReturn(1000);
		//这里并未传入10，单元测试报错
		int ret = creditServer.getUserCredit(11);
		assertEquals(1000, ret);
	}

	@Test
	@Ignore
	public void test3() {
		CreditSystemService creditServer = mock(CreditSystemService.class);
		int id = 10;
		when(creditServer.getUserCredit(eq(id))).thenReturn(1000);
		int ret = creditServer.getUserCredit(id);
		ret = creditServer.getUserCredit(id);
		assertEquals(1000, ret);
		//verify包含了模拟的对象和期望调用的次数，使用times来构造希望调用的次数，下面是期望调用2次
		verify(creditServer, times(2)).getUserCredit(eq(id));
	}

	@Test
	public void test4() {
		CreditSystemService creditServer = mock(CreditSystemService.class);
		int id = 10;
		when(creditServer.getUserCredit(eq(id))).thenReturn(1000);
		when(creditServer.addCredit(eq(id), anyInt())).thenReturn(true);
		//实际调用，先获取用户积分，然后增加10分
		int ret = creditServer.getUserCredit(id);
		creditServer.addCredit(id, ret+10);

		//验证调用顺序，确保模拟对象先被调用getUserCredit，然后再调用addCredit
		InOrder inOrder = inOrder(creditServer);
		inOrder.verify(creditServer).getUserCredit(id);
		inOrder.verify(creditServer).addCredit(id, ret+10);
	}

}

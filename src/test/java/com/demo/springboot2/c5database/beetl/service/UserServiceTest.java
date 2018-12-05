package com.demo.springboot2.c5database.beetl.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;
	@MockBean
	private CreditSystemService creditSystemService;

	@Test
	public void testGetCredit() {
		int expectedCredit = 200;
		//given用来模拟一个service方法的调用返回，anyInt指示了可以传入任何参数，willReturn方法说明这个调用将返回200
		given(creditSystemService.getUserCredit(anyInt())).willReturn(expectedCredit);
		int credit = userService.getCredit(1);
		assertEquals(expectedCredit, credit);
	}

}

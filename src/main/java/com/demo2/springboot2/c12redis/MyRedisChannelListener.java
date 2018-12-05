package com.demo2.springboot2.c12redis;

import java.io.UnsupportedEncodingException;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class MyRedisChannelListener implements MessageListener {

	@Override
	//可以通过getBody获取内容，返回的是字节数组，因为我们在发送消息时使用的StringRedisTemplate，也就是发送消息的时候，消息编码就是字符通过
	//utf-8转成字节，因此我们反过来直接使用utf-8构造字符串即可。getChannel可以得到消息所在频道，springboot通常需要一个MessageListener实现redfis消息监听。
	//因此，应用有时候需要根据channel名字来区分。
	public void onMessage(Message message, byte[] arg1) {
		byte[] channel = message.getChannel();
		byte[] bs = message.getBody();
		try {
			String content = new String(bs, "utf-8");
			String p = new String(channel, "utf-8");
			System.out.println("get " + content + " from " + p);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}

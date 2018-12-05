package com.demo2.springboot2.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Util {

	//生成md5的结果
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";//加密方式
		Object crdentials = "123456";//密码原值
		//ByteSource提供了一个内部方法，可以将字符串转换为对应的盐值信息。一般情况下我们使用一个唯一的字符串作为盐值。在本测试样例中，我们使用用户名作为盐的原始值。
		ByteSource salt = ByteSource.Util.bytes("张三");
		int hashIterations = 1024;//加密1024次
		Object result = new SimpleHash(hashAlgorithmName, crdentials, salt, hashIterations);
		System.out.println(result);
	}

}

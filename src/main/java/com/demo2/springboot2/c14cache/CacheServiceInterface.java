package com.demo2.springboot2.c14cache;

import org.springframework.stereotype.Service;
import com.demo2.springboot2.c14cache.domain.Menu;
import com.demo2.springboot2.c14cache.domain.User;
import com.demo2.springboot2.c14cache.domain.UserExt;

public interface CacheServiceInterface {

	//声明方法结果是可以被缓存的，如果缓存存在，则目标方法不会被调用，如果缓存不存在，调用目标方法，同时把结果缓存
	//可以声明多个缓存，如果至少有一个缓存有缓存项，则器缓存项将被返回
	//如果没有参数，则缓存的key就是SimpleKey.EMPTY
	public abstract String getMenu();

	//缓存key：
	//如果只有一个参数则该参数就是key
	//如果有多个参数则多个参数组成key
	public abstract String getMenu2(Integer id);

	//还可以自己实现key
	public abstract String getMenu3(Menu menu);

	//总是会执行方法体，并且使用返回结果更新缓存
	public abstract Menu updateMenu(Menu menu);

	//清空缓存
	//如果要清空所有key，使用allEntries=true
	public abstract void updateUser(Long id, int status);

	//Caching可以混合以上各个注解，比如一个修改同时需要失效对应的用户缓存和用户扩展信息缓存
	public abstract void updateUser2(User user, UserExt ext);

}
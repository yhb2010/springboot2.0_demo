package com.demo2.springboot2.c14cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.demo2.springboot2.c14cache.domain.Menu;
import com.demo2.springboot2.c14cache.domain.User;
import com.demo2.springboot2.c14cache.domain.UserExt;

@Service
//可以在类上统一配置cache名称和keyGenerator
//@CacheConfig(cacheNames="menu", keyGenerator="myKey")
public class CacheService implements CacheServiceInterface {

	//声明方法结果是可以被缓存的，如果缓存存在，则目标方法不会被调用，如果缓存不存在，调用目标方法，同时把结果缓存
	//可以声明多个缓存，如果至少有一个缓存有缓存项，则器缓存项将被返回
	//如果没有参数，则缓存的key就是SimpleKey.EMPTY
	/* (non-Javadoc)
	 * @see com.demo2.springboot2.c14cache.CacheServiceInterface#getMenu()
	 */
	@Override
	@Cacheable(cacheNames={"menu", "menuExt"}, keyGenerator="myKey2")
	public String getMenu(){
		System.out.println("进入方法");
		return "获取菜单";
	}

	//缓存key：
	//如果只有一个参数则该参数就是key
	//如果有多个参数则多个参数组成key
	/* (non-Javadoc)
	 * @see com.demo2.springboot2.c14cache.CacheServiceInterface#getMenu2(java.lang.Integer)
	 */
	@Override
	@Cacheable({"menu2"})
	public String getMenu2(Integer id){
		System.out.println("进入方法2");
		return "获取菜单2";
	}

	//还可以自己实现key
	/* (non-Javadoc)
	 * @see com.demo2.springboot2.c14cache.CacheServiceInterface#getMenu3(com.demo2.springboot2.c14cache.domain.Menu)
	 */
	@Override
	@Cacheable(cacheNames={"menu3"}, keyGenerator="myKey")
	public String getMenu3(Menu menu){
		System.out.println("进入方法3");
		return "获取菜单3";
	}

	//使用SpEL表达式更方便：
	//@Cacheable(cacheNames={"menu3"}, key="#orgId")
	//@Cacheable(cacheNames={"menu3"}, key="#uer.orgId")
	//@Cacheable(cacheNames={"menu3"}, condition="#orgId>1000")

	//总是会执行方法体，并且使用返回结果更新缓存
	/* (non-Javadoc)
	 * @see com.demo2.springboot2.c14cache.CacheServiceInterface#updateMenu(com.demo2.springboot2.c14cache.domain.Menu)
	 */
	@Override
	@CachePut(cacheNames = {"org"})
	public Menu updateMenu(Menu menu){
		return new Menu();
	}

	//清空缓存
	//如果要清空所有key，使用allEntries=true
	/* (non-Javadoc)
	 * @see com.demo2.springboot2.c14cache.CacheServiceInterface#updateUser(java.lang.Long, int)
	 */
	@Override
	@CacheEvict(cacheNames={"user"}, key="#id")
	public void updateUser(Long id, int status){

	}

	//Caching可以混合以上各个注解，比如一个修改同时需要失效对应的用户缓存和用户扩展信息缓存
	/* (non-Javadoc)
	 * @see com.demo2.springboot2.c14cache.CacheServiceInterface#updateUser2(com.demo2.springboot2.c14cache.domain.User, com.demo2.springboot2.c14cache.domain.UserExt)
	 */
	@Override
	@Caching(evict = {@CacheEvict(cacheNames="user", key="#user.id"), @CacheEvict(cacheNames="userExt", key="#ext.id")})
	public void updateUser2(User user, UserExt ext){

	}

}

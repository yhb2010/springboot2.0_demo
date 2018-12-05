package com.demo2.springboot2.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**shrio的回话管理
 * Shiro提供SessionDAO用于会话的CRUD，即DAO（Data Access Object）模式实现。
 * AbstractSessionDAO提供了SessionDAO的基础实现，如生成会话ID等；
 * CachingSessionDAO提供了对开发者透明的会话缓存的功能，只需要设置相应的CacheManager即可；
 * @author DELL
 *
 */
public class RedisSessionDao extends AbstractSessionDAO {

	@Autowired
    private RedisManager redisManager;

    @Override
    public void delete(Session session) {
        if(session == null || session.getId() == null){
            System.out.println("Session is null");
            return;
        }
        redisManager.hdelete(session.getId().toString());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        List<Session> list = redisManager.hmget();
        return list;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if(session == null || session.getId() == null){
            System.out.println("Session is null");
            return;
        }
        Serializable sessionId = session.getId();
        redisManager.hadd(sessionId.toString(), session);
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        //将生成的sessionId和session进行捆绑
        assignSessionId(session, sessionId);
        //添加进redis
        redisManager.hadd(sessionId.toString(), session);

        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
    	//这里会被多次调用，解决办法是重写DefaultWebSessionManager的
    	System.out.println("从redis获取信息sessionId：" + sessionId);
    	System.out.println("从redis获取信息：" + redisManager.hget(sessionId.toString()));
        return redisManager.hget(sessionId.toString());
    }

}

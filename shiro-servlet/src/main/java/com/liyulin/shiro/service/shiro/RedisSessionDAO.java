package com.liyulin.shiro.service.shiro;

import lombok.RequiredArgsConstructor;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author collin
 * @date 2022-06-08
 */
@RequiredArgsConstructor
public class RedisSessionDAO extends CachingSessionDAO {

    private final RedissonClient redissonClient;
    //有效期（后续使用时会增加时间单位）
    private static final int EXPRIE = 1800;
    private static final String CACHE_NAME = "lp:cache:session";

    /**
     * shiro创建session时，将session保存到redis
     *
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        System.err.println("sessionid1=====>"+session.getId());
        //生成SessionID
        Serializable serializable = this.generateSessionId(session);
        assignSessionId(session, serializable);
        System.err.println("sessionid2=====>"+session.getId());
        //将sessionid作为Key，session作为value存入redis
        redissonClient.getMapCache(CACHE_NAME).put(serializable, session, EXPRIE, TimeUnit.SECONDS);
        return serializable;
    }

    /**
     * 当用户维持会话时，刷新session的有效时间
     *
     * @param session
     */
    @Override
    protected void doUpdate(Session session) {
        //设置session有效期
        session.setTimeout(EXPRIE * 1000);
        redissonClient.getMapCache(CACHE_NAME).put(session.getId(), session, EXPRIE, TimeUnit.SECONDS);
    }

    /**
     * 当用户注销或会话过期时，将session从redis中删除
     *
     * @param session
     */
    @Override
    protected void doDelete(Session session) {
        //null 验证
        if (session == null) {
            return;
        }
        //从Redis中删除指定SessionId的k-v

        redissonClient.getMapCache(CACHE_NAME).remove(session.getId());
    }

    /**
     * shiro通过sessionId获取Session对象，从redis中获取
     *
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            return null;
        }
        return (Session)redissonClient.getMapCache(CACHE_NAME).get(sessionId);
    }

}
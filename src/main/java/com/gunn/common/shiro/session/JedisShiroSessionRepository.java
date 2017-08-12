package com.gunn.common.shiro.session;

import com.gunn.common.shiro.cache.JedisManager;
import com.gunn.common.util.LoggerUtils;
import com.gunn.common.util.SerializeUtil;
import com.sun.xml.internal.ws.server.ServerRtException;
import org.apache.shiro.session.Session;

import java.io.Serializable;


/**
 * 通过Jedis操作redis中的session
 */
public class JedisShiroSessionRepository implements ShiroSessionRepository {

    private JedisManager jedisManager;

    private static final int REDIS_SHIRO_SESSION_DBINDEX = 1;

    private static final int REDIS_SHIRO_SESSION_EXPIRETIME = 30;

    //private static final String REDIS_SHIRO_SESSION = "gunn-shiro-session";


    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }

    @Override
    public void saveSession(Session session) {
        if (session == null) {
            throw new NullPointerException("session is null");
        }
        try {

            byte[] key = SerializeUtil.serialize(session.getId());


            jedisManager.saveValueByKey(REDIS_SHIRO_SESSION_DBINDEX, SerializeUtil.serialize(session.getId()), SerializeUtil.serialize(session), REDIS_SHIRO_SESSION_EXPIRETIME);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "save session error，id:[%s]",session.getId());
        }
    }

    @Override
    public void deleteSession(Session session) {
        if (session == null) {
            throw new NullPointerException("session is empty");
        }
        try {
            jedisManager.deleteByKey(1, SerializeUtil.serialize((session.getId())));
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "删除session出现异常，id:[%s]",session.getId());
        }
    }

    @Override
    public Session getSession(Serializable sessionId) {
        if (sessionId == null) {
            throw new NullPointerException("session id is empty");
        }
        Session session = null;
        try {
            byte[] key = SerializeUtil.serialize(sessionId);
            byte[] value = jedisManager.getValueByKey(REDIS_SHIRO_SESSION_DBINDEX, key);
            session = SerializeUtil.deserialize(value,Session.class); //反序列化
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "获取session异常，id:[%s]",sessionId);
        }
        return session;
    }

//    public String buildRedisSessionKey(Serializable sessionId) {
//        return REDIS_SHIRO_SESSION + sessionId;
//    }
}

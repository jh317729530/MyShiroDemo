package com.gunn.common.shiro.session;

import com.gunn.common.shiro.cache.JedisManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import java.io.Serializable;
import java.util.Collection;

public class CustomShiroSessionDAO extends AbstractSessionDAO {

    private JedisManager jedisManager;

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }

    /**
     * 创建完session后会调用此方法
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        if (session == null || session.getId() == null) {
            throw new NullPointerException("session is empty");
        }
        return null;
    }

    /**
     * 根据会话ID获取会话
     * @param serializable
     * @return
     */
    @Override
    protected Session doReadSession(Serializable serializable) {
        return null;
    }

    /**
     * 更新会话
     * @param session
     * @throws UnknownSessionException
     */
    @Override
    public void update(Session session) throws UnknownSessionException {

    }

    /**
     * 删除会话
     * @param session
     */
    @Override
    public void delete(Session session) {

    }

    /**
     * 获取当前所有活跃用户，如果用户量多此方法影响性能
     * @return
     */
    @Override
    public Collection<Session> getActiveSessions() {
        return null;
    }
}

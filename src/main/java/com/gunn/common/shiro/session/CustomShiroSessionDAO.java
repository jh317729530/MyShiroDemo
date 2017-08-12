package com.gunn.common.shiro.session;

import com.gunn.common.shiro.cache.JedisManager;
import com.gunn.common.util.LoggerUtils;
import com.gunn.common.util.SerializeUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import java.io.Serializable;
import java.util.Collection;

public class CustomShiroSessionDAO extends AbstractSessionDAO {

    public static final String REDIS_SHIRO_SESSION = "gunn-shiro-session";

    private static final int SESSION_VAL_TIME_SPAN = 18000;

    private static final int DB_INDEX = 1;

    private ShiroSessionRepository sessionRepository;

    public ShiroSessionRepository getSessionRepository() {
        return sessionRepository;
    }

    public void setSessionRepository(ShiroSessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /**
     * 创建完session后会调用此方法
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        sessionRepository.saveSession(session);
        return session.getId();
    }

    /**
     * 根据会话ID获取会话
     * @param serializable
     * @return
     */
    @Override
    protected Session doReadSession(Serializable serializable) {
        return sessionRepository.getSession(serializable);

    }

    /**
     * 更新会话
     * @param session
     * @throws UnknownSessionException
     */
    @Override
    public void update(Session session) throws UnknownSessionException {
        sessionRepository.saveSession(session);
    }

    /**
     * 删除会话
     * @param session
     */
    @Override
    public void delete(Session session) {
        if (session == null) {
            LoggerUtils.error(getClass(), "Session 不能为null");
            return;
        }
        Serializable id = session.getId();
        if (id != null) {
            sessionRepository.deleteSession(session);
        }
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

package com.gunn.common.shiro.listener;

import com.gunn.common.shiro.session.ShiroSessionRepository;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * 自定义的session监听器
 */
public class CustomShiroSessionListener implements SessionListener {

    private ShiroSessionRepository shiroSessionRepository;

    public ShiroSessionRepository getShiroSessionRepository() {
        return shiroSessionRepository;
    }

    public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
        this.shiroSessionRepository = shiroSessionRepository;
    }

    /**
     * 一个会话的生命周期开始
     * @param session
     */
    @Override
    public void onStart(Session session) {
        System.out.println("session:"+session.getId()+"开始");
    }

    /**
     * 一个会话的生命周期结束
     * @param session
     */
    @Override
    public void onStop(Session session) {
        System.out.println("session:" + session.getId() + "结束");
    }

    /**
     * session过期，删除redis中的session
     * @param session
     */
    @Override
    public void onExpiration(Session session) {
        shiroSessionRepository.deleteSession(session);
    }
}

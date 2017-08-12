package com.gunn.common.shiro.session;


import org.apache.shiro.session.Session;

import java.io.Serializable;

/**
 *     抽象出对session的操作，当由redis管理session改为数据库管理session，将会变得很方便
 *     实现解耦
 */
public interface ShiroSessionRepository {

    /**
     * 保存或更改session
     * @param session
     */
    void saveSession(Session session);

    /**
     * 删除session
     * @param session
     */
    void deleteSession(Session session);

    /**
     *  获取session
     * @param sessionId
     * @return
     */
    Session getSession(Serializable sessionId);


}

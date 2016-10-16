package com.lyk.myspring.mina.bean;

import org.apache.mina.core.session.IoSession;

import java.util.concurrent.ConcurrentHashMap;

/**
* @ClassName: UserSessionMap
* @author victor_freedom (x_freedom_reddevil@126.com)
* @createddate Dec 15, 2014 12:43:10 AM
* @Description: TODO
 */
public class UserSessionMap extends ConcurrentHashMap<String, IoSession> {

    private static UserSessionMap sessionMap;

    private static final class Single {
        private static final UserSessionMap SESSION_MAP = new UserSessionMap();
    }

    private UserSessionMap() {
        super(500);//set max size for queue
    }

    public static UserSessionMap getInstance() {
        if (sessionMap == null)
            sessionMap = Single.SESSION_MAP;
        return sessionMap;
    }
}
package com.demo2.springboot2.shiro;

import java.io.Serializable;

import javax.servlet.ServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

public class CustomSessionManager extends DefaultWebSessionManager {

	@Override
	//sessionKey里实际存储的是request对象
	protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
		Serializable sessionId = getSessionId(sessionKey);
		ServletRequest request = null;
		if(sessionKey instanceof WebSessionKey){
			request = ((WebSessionKey)sessionKey).getServletRequest();
		}
		if (request != null && sessionId != null && request.getAttribute(sessionId.toString()) != null) {
			return (Session)request.getAttribute(sessionId.toString());
		}
		Session session = super.retrieveSession(sessionKey);
		if (request != null && sessionId != null) {
			request.setAttribute(sessionId.toString(), session);
		}
		return session;
	}

}

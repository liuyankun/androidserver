package com.lyk.myspring.mina.handler;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.alibaba.fastjson.JSONObject;
import com.lyk.myspring.mina.bean.UserSessionMap;

public class MyServerHandler extends IoHandlerAdapter {

	private final UserSessionMap userSessionMap = UserSessionMap.getInstance();

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("exceptionCaught");
		super.exceptionCaught(session, cause);
	}
	@Override
	public void inputClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("inputClosed");
		super.inputClosed(session);
	}
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("messageReceived");
		String data = URLDecoder.decode(message.toString(), "UTF-8").trim();
		JSONObject jsonObject = JSONObject.parseObject(data);
		String sender = jsonObject.getString("sender");
		String type = jsonObject.getString("type");
		System.err.println(data);
		if ("LOGIN".equals(type)) {
			userSessionMap.put(sender, session);
			for (String key : userSessionMap.keySet()) {
				if (key.equals(sender))
					continue;
				IoSession ioSession = userSessionMap.get(key);
				ioSession.write(URLEncoder.encode("已上线", "UTF-8") + "\r\n");
			}
		} else if ("OFFLINE".equals(type)) {
			userSessionMap.remove(sender);
			for (String key : userSessionMap.keySet()) {
				IoSession ioSession = userSessionMap.get(key);
				ioSession.write(URLEncoder.encode("已下线", "UTF-8") + "\r\n");
			}
		} else {
			String receiver = jsonObject.getString("receiver");
			IoSession receiverSession = userSessionMap.get(receiver);
			if (receiverSession == null) {
				session.write(URLEncoder.encode("用户不在线", "UTF-8") + "\r\n");
			} else {
				receiverSession.write(URLEncoder.encode(data, "UTF-8") 
						+ "\r\n");
			}
		}
		super.messageReceived(session, message);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("messageSent");
		super.messageSent(session, message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("sessionClosed");
		super.sessionClosed(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("sessionCreated");
		super.sessionCreated(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("sessionIdle");
		super.sessionIdle(session, status);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("sessionOpened");
		super.sessionOpened(session);
	}

}

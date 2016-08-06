package com.lyk.myspring.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.lyk.myspring.msg.model.NewsItem;
import com.lyk.myspring.msg.model.NewsMsg;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import com.thoughtworks.xstream.XStream;

import sun.print.resources.serviceui_zh_TW;

public class MessageUtil {

	public static String newMessageToXml(NewsMsg newsMsg){
		XStream xStream = new XStream();
		xStream.alias("xm", newsMsg.getClass());
		xStream.alias("item", new NewsItem().getClass());
		return xStream.toXML(newsMsg);
	}
	public static String initNewsMsg(String toUserName,String fromUserName){
		String msg = null;
		List<NewsItem> newsItems=new ArrayList<>();
		NewsMsg newsMsg = new NewsMsg();
		
		NewsItem news = new NewsItem();
		news.setTitle("马刺介绍");
		news.setDescription("sdfadsfasfasdfadsf");
		news.setPicurl("/image/spurs.jpg");		
		return msg;
		
	}
	
	public static String getSha1(String str){
		System.out.println(str);
		if(str == null || str.length()==0){
			return null;
		}
		char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));
			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j*2];
			int k=0;
			for(int i=0; i<j; i++){
				byte byte0=md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}
}

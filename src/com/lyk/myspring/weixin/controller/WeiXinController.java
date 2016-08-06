package com.lyk.myspring.weixin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lyk.myspring.utils.MessageUtil;

@RequestMapping("/weixin")
@Controller
public class WeiXinController {
	private static final String token = "spurs";

	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public void verify(HttpServletResponse resp, String signature, String timestamp, String nonce, String echostr) {

		String[] arr = new String[] { token, timestamp, nonce };
		// 排序
		Arrays.sort(arr);
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}

		try {
			PrintWriter out = resp.getWriter();
			String temp = MessageUtil.getSha1(content.toString());
			if (signature.equals(temp)) {
				out.print(echostr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

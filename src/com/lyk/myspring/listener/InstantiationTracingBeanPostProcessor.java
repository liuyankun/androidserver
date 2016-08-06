package com.lyk.myspring.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent>{
	@Override
	  public void onApplicationEvent(ContextRefreshedEvent event) {
	      if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.
	           
	      }
	   //   System.out.println("=======qidon");
	  }
}

package com.lyk.myspring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
//@Aspect
public class ServiceAspect {

	@Pointcut("execution(* com.lyk.myspring.user.service..*(..))")
	public void printLogs(){}
	
	@Before(value = "printLogs ()")
	public void befor(){
		System.out.println("-----------before--------");
	}
	
	@After(value = "printLogs ()")
	public void after(){
		System.out.println("-----------after----------");
	}
	@AfterReturning(value = "printLogs ()")
	public void afterReturning(){
		System.out.println("-----------AfterReturning----------");
	}
	@Around(value = "printLogs ()")
	public Object around(ProceedingJoinPoint joinPoint)throws Throwable{
		
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i].getClass()+":"+args[i]);
		}
		System.out.println("-----------around start ---------");
		Object object=  joinPoint.proceed();
		System.out.println("返回对象的类型为："+object.getClass().getName());
		System.out.println("-----------around end ---------");
		return object;
	}
	@AfterThrowing(value = "printLogs()")
	public void afterThrowing(){
		System.out.println("-----------afterThrowing---------");
	}
}   

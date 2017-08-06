package com.xb.petstore.inventory.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExampleAspect {
	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long start = System.currentTimeMillis();
		Object  proceed= joinPoint.proceed();
		long executionTime= System.currentTimeMillis() - start;
		
		System.out.println(joinPoint.toString());
		System.out.println(joinPoint.getSignature()+ "execute in "+ executionTime +"ms");
		
	    return proceed;
	}
	
	@Before("@annotation(LogExecutionTime)")
	public void logBefore(JoinPoint joinPoint) throws Throwable {
		
		
		System.out.println("logBefore");
		System.out.println(joinPoint.toString());
		System.out.println(joinPoint.getArgs());
		System.out.println(joinPoint.getSignature().getName());
		
	}
	@AfterReturning(pointcut="@annotation(LogExecutionTime)",returning= "result")
	public void logAfter(JoinPoint joinPoint, Object result) throws Throwable {
		
		System.out.println("logAfter");
		System.out.println(joinPoint.getArgs());
		System.out.println(joinPoint.getSignature().getName());
		System.out.println(result);
		
	}
}

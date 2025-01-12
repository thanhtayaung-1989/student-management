package com.student.student_management.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class StudentBatchAspect {

	@Pointcut("execution(* com.student.student_management.service.impl.StudentRecordService.*(..))")
	public void loggingPointCut() {
		
	}
	
	@Before("loggingPointCut()")
	public void before(JoinPoint joinPoint) {
		log.info("Before method invoked " + joinPoint.getSignature());
		
	}
	
	@AfterReturning("loggingPointCut()")
	public void after(JoinPoint joinPoint) {
		log.info("After method invoked " + joinPoint.getSignature());
		
	}
}

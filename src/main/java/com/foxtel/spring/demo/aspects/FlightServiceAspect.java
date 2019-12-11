package com.foxtel.spring.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Configuration
@Component
public class FlightServiceAspect {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	/**
	 * Pointcut method
	 */
	@Pointcut("execution(* com.foxtel.spring.demo.service.FlightService.*(..))")
	private void serviceExection() {
	}
	
	@Before("serviceExection()")
	public void beforeService(JoinPoint joinPoint) {
		
		logger.debug("Service requested for flights data " +joinPoint.getTarget().getClass().getName());
	}
	
	@After("serviceExection()")
	public void afterService(JoinPoint joinPoint) {
		
		logger.debug("Service successfully returned flights data ", joinPoint.getTarget().getClass().getName());
	}

}

package com.foxtel.spring.demo.aspects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Configuration
public class FlightControllerAspect {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Value("${spring.mvc.async.request-timeout}")
	private long timeout;

	/**
	 * Around advice to log the response time of each endpoint
	 */
	@Around("profileEndpointMethods()")
		// && execution(@com.foxtel.spring.demo.security.interfaces.EnableAccessControl * *(..)) && @annotation(accessControl)")
	public Object profile(ProceedingJoinPoint proceedingJoinPoint)
		// , EnableAccessControl accessControl)
		
			throws Throwable {
		StopWatch stopWatch = new StopWatch(getClass().getSimpleName());

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();

		try {
			stopWatch.start(proceedingJoinPoint.getSignature().getName());
		} finally {
			// if (accessControl.authUserName() == "admin" && accessControl.authPassword() == "admin") {

				proceedingJoinPoint.proceed();

				logger.info("REQUEST URI " + request.getRequestURI());
				logger.info("REQUEST method " + request.getMethod());
				logger.debug("REQUEST session ID " + request.getSession().getId());

				logger.debug("REQUEST session creation time " + request.getSession().getCreationTime());
				logger.debug("REQUEST session last accessed time " + request.getSession().getLastAccessedTime());
				
				logger.debug("REQUEST session creation time " + request.getSession().getCreationTime());
				logger.debug(
					"REQUEST session servlet context " + request.getSession().getServletContext().getContextPath());
				logger.debug("REQUEST session servlet context server info "
				+ request.getSession().getServletContext().getServerInfo());
				logger.debug("REQUEST session servlet context timeout "
				+ request.getSession().getServletContext().getSessionTimeout() + " minutes");
				
				logger.debug("Reuqest time out set at " + timeout + " ms");

				stopWatch.stop();
				logger.debug(stopWatch.prettyPrint());
				logger.debug("API Running time: " +stopWatch.getTotalTimeMillis());
				if (stopWatch.getTotalTimeMillis() > timeout) {

					response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
					response.sendError(HttpStatus.REQUEST_TIMEOUT.value(), "Server took too long to respond");
					logger.error(response.getStatus() + " Request timed out after " + timeout + " ms");
				}

			// } else {
			// 	response.sendError(HttpStatus.UNAUTHORIZED.value(), " Authorisation error");
			// 	logger.error(response.getStatus() + " !!!!!!!! <<YOU SHALL NOT PASS>> !!!!!!!! ");
			// }
		}
		logger.info("HTTP Status: " +response.getStatus());
		return proceedingJoinPoint.proceed();
	}

	/**
	 * Pointcut method to intercept all controller methods when any API endpoint is
	 * called
	 */
	@Pointcut("execution(* com.foxtel.spring.demo.controller.FlightController.*(..))")
	public void profileEndpointMethods() {
	}

}

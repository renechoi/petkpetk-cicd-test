package com.petkpetk.service.config.trace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.petkpetk.service.config.trace.LogTrace;
import com.petkpetk.service.config.trace.TraceStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class GlobalTraceHandler {

	private final LogTrace logTrace;

	// @Around("@annotation(com.petkpetk.service.config.annotation.Trace)")
	@Around("com.petkpetk.service.config.trace.GlobalTraceHandler.all()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		TraceStatus status = null;
		Object result = null;

		try {
			String message = joinPoint.getSignature().toShortString();
			status = logTrace.begin(message);

			result = joinPoint.proceed();
			logTrace.end(result, status);
			return result;
		} catch (Exception exception) {
			logTrace.exception(result, status, exception);
			throw exception;
		}

	}

	@Pointcut("execution(* com.petkpetk.service.domain..*(..))")
	public void all(){}
}

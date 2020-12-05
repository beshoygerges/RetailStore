package com.blackstoneeit.retailstore.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Log
@Aspect
@Component
public class PerformanceLoggingAspect {

    @Around(" @annotation(com.blackstoneeit.retailstore.annotation.Loggable)")
    public Object validateAspect(ProceedingJoinPoint pjp) throws Throwable {
        LocalDateTime start = LocalDateTime.now();
        try {

            Object response = pjp.proceed();
            return response;

        } catch (Throwable e) {
            throw e;
        } finally {
            LocalDateTime end = LocalDateTime.now();
            log.info("Request time is " + ChronoUnit.MILLIS.between(start, end) + " ms");
        }

    }
}

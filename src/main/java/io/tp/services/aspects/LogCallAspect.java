package io.tp.services.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogCallAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogCallAspect.class);

    @Around(value = "@annotation(annotation)")
    public Object LogCall(final ProceedingJoinPoint joinPoint, final LogCall annotation) throws Throwable {
        LOGGER.info("Called: " + joinPoint.getSignature());
        return joinPoint.proceed();
    }
}
package paradox.store.global.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    private static AtomicLong counter = new AtomicLong();

    @Pointcut("execution(* paradox.store.domain.service..*.*(..))")
    public void storeServiceMethodLogging() {}

    @Around("storeServiceMethodLogging()")
    public Object serviceMethodLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("Entering {} by {}", joinPoint.getSignature().getName(), counter.get());
        Object result = joinPoint.proceed();
        log.debug("Exiting {} by {}", joinPoint.getSignature().getName(), counter.getAndIncrement());
        return result;
    }
}

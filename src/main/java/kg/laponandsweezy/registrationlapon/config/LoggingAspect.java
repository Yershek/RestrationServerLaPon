package kg.laponandsweezy.registrationlapon.config;

//TODO: Создать Лавсчик ошибок
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* kg.laponandsweezy.registrationlapon.service..*(..)) || " +
            "execution(* kg.laponandsweezy.registrationlapon.controller..*(..))")
    public void applicationPackagePointcut() {
    }

    @Before("applicationPackagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("==> Entering: {} | Args: {}",
                joinPoint.getSignature().toShortString(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String resultString = (result != null) ? result.getClass().getSimpleName() : "null";
        log.info("<== Exiting: {} | Result Type: {}",
                joinPoint.getSignature().toShortString(),
                resultString);
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.error("!!! Exception: {} | Message: {}",
                joinPoint.getSignature().toShortString(),
                error.getMessage());
    }
}
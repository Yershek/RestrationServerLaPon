//package kg.laponandsweezy.registrationlapon.config;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Aspect
//@Component
//public class LoggingAspect {
//
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    @Before("execution(* kg.laponandsweezy.registrationlapon.service..*(..)) || execution(* kg.laponandsweezy.registrationlapon.controller..*(..))")
//    public void logBefore(JoinPoint joinPoint) {
//        log.info("Entering method: {} with arguments: {}", joinPoint.getSignature().toShortString(), Arrays.toString(joinPoint.getArgs()));
//    }
//
//    @AfterReturning(pointcut = "execution(* kg.laponandsweezy.registrationlapon.service..*(..)) || execution(* kg.laponandsweezy.registrationlapon.controller..*(..))", returning = "result")
//    public void logAfterReturning(JoinPoint joinPoint, Object result) {
//        log.info("Exiting method: {} with result: {}", joinPoint.getSignature().toShortString(), result);
//    }
//
//    @AfterThrowing(pointcut = "execution(* kg.laponandsweezy.registrationlapon.service..*(..)) || execution(* kg.laponandsweezy.registrationlapon.controller..*(..))", throwing = "error")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
//        log.error("Exception in method: {} with cause: {}", joinPoint.getSignature().toShortString(), error.getMessage() != null ? error.getMessage() : "NULL");
//    }
//}

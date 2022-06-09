package fr.airfrance.userregistrationmanagement.configuration;

import java.util.Arrays;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {

  private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  @Pointcut("execution(* fr.test.useraccountmanagement.service.implementations.*.*(*))")
  private void userService() {
  }

  /**
   *
   * @param joinPoint
   */
  @Before("userService()")
  public void doBeforeTask(JoinPoint joinPoint) {
    if (logger.isInfoEnabled()) {
      logger.info(joinPoint.getSignature().toShortString());
      List<Object> args = Arrays.asList(joinPoint.getArgs());
      if (!args.isEmpty()) {
        args.forEach(arg -> logger.info("[IN " + arg.getClass().getSimpleName() + "]: " + arg.toString()));
      }
    }
  }

  /**
   *
   * @param retVal
   */
  @AfterReturning(pointcut = "userService()", returning = "retVal")
  public void doAfterReturn(Object retVal) {
    if (retVal != null) {
      logger.info("[OUT " + retVal.getClass().getSimpleName() + "]: " + retVal.toString());
    }
    logger.info("====================");
  }

  /**
   *
   * @param error
   */
  @AfterThrowing(pointcut = "userService()", throwing = "error")
  public void doAfterReturn(Throwable error) {
    if (error != null) {
      logger.error(error.getClass().getSimpleName());
    }
    logger.info("====================");
  }

  /**
   *
   * @param joinPoint
   * @return
   * @throws Throwable
   */
  @Around("within(fr.test.useraccountmanagement.controller.*)")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    Object object;
    long startTime = System.currentTimeMillis();
    try {
      object = joinPoint.proceed();
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw e;
    }
    long timeTaken = System.currentTimeMillis() - startTime;
    logger.info("Time Taken by {} is {} {}", joinPoint, timeTaken, " ms");
    return object;
  }
}

package com.miicrown.aspect;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

	public static final Logger log = LoggerFactory.getLogger(LogAspect.class);
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");
	public static final Calendar calendar = Calendar.getInstance();
	
	@Pointcut("execution(* com.miicrown..*.*(..))")
	public void PointCut(){}
	
	/**
     * 前置通知：目标方法执行之前执行以下方法体的内容 
     * @param jp
     */
    @Before(value="PointCut()")	
    public void beforeMethod(JoinPoint jp){
        log.info(String.format("[Before         ], method[ %s ], params[ %s ]", jp.getSignature().getName(), Arrays.asList(jp.getArgs())));
    }
 
    /**
     * 返回通知：目标方法正常执行完毕时执行以下代码
     * @param jp
     * @param result
     */
    @AfterReturning(value="PointCut()",returning="result")
    public void afterReturningMethod(JoinPoint jp, Object result){
        log.info(String.format("[After returning], method[ %s ], result[ %s ]", jp.getSignature().getName(), result));
    }
 
    /**
     * 后置通知：目标方法执行之后执行以下方法体的内容，不管是否发生异常。
     * @param jp
     */
    @After(value="PointCut()")
    public void afterMethod(JoinPoint jp){
    	log.info(String.format("[After          ], method[ %s ]", jp.getSignature().getName()));
    }
 
    /**
     * 异常通知：目标方法发生异常的时候执行以下代码
     */
    @AfterThrowing(value="PointCut()",throwing="e")
    public void afterThorwingMethod(JoinPoint jp, NullPointerException e){
        log.info(String.format("[After throwing  ], method[ %s ], result[ %s ]", jp.getSignature().getName(), e));
    }
 
//  /**
//   * 环绕通知：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
//   * @return 
//   */
//  @Around(value="PointCut()")
//  public Object aroundMethod(ProceedingJoinPoint jp){
//      String methodName = jp.getSignature().getName();
//      Object result = null;
//      try {
//    	  log.info(String.format("[%s] [Before], method[ %s ], params[ %s ]", sdf.format(calendar.getTime()), methodName, Arrays.asList(jp.getArgs())));
//          //执行目标方法
//          result = jp.proceed();
//          log.info(String.format("[%s] [After returning], method[ %s ], result[ %s ]", sdf.format(calendar.getTime()), methodName, result));
//      } catch (Throwable e) {
//    	  log.info(String.format("[%s] [After throwing], method[ %s ], result[ %s ]", sdf.format(calendar.getTime()), methodName, e));
//      }
//      
//      log.info(String.format("[%s] [After], method[ %s ]", sdf.format(calendar.getTime())));
//      return result;
//  }

	
}

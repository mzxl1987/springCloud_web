package com.miicrown.aspect;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.miicrown.entity.dto.Result;
import com.miicrown.enums.ErrorCode;


@Aspect
@Component
public class LogAspect {

	public static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
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
        logger.info(String.format("[Before         ], method[ %s ], params[ %s ]", jp.getSignature().getName(), Arrays.asList(jp.getArgs())));
    }
 
    /**
     * 返回通知：目标方法正常执行完毕时执行以下代码
     * @param jp
     * @param result
     */
    @AfterReturning(value="PointCut()",returning="result")
    public void afterReturningMethod(JoinPoint jp, Object result){
        logger.info(String.format("[After returning], method[ %s ], result[ %s ]", jp.getSignature().getName(), result));
    }
 
    /**
     * 后置通知：目标方法执行之后执行以下方法体的内容，不管是否发生异常。
     * @param jp
     */
    @After(value="PointCut()")
    public void afterMethod(JoinPoint jp){
    	logger.info(String.format("[After          ], method[ %s ]", jp.getSignature().getName()));
    }
 
    /**
     * 异常通知：目标方法发生异常的时候执行以下代码
     */
    @AfterThrowing(value="PointCut()",throwing="e")
    public void afterThorwingMethod(JoinPoint jp, NullPointerException e){
        logger.info(String.format("[After throwing  ], method[ %s ], result[ %s ]", jp.getSignature().getName(), e));
    }
 
  /**
   * 环绕通知：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
   * @return 
   */
    @Around(value="PointCut()")
    public Object aroundMethod(ProceedingJoinPoint pjp) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        Object result;
        try {
            logger.info("开始: " + pjp.getSignature() + " 参数：" + Lists.newArrayList(pjp.getArgs()).toString());
            result = pjp.proceed(pjp.getArgs());
            logger.info("结束: " + pjp.getSignature() + "， 返回值：" + result);
            logger.info("耗时：" + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS) + "(毫秒).");
        } catch (Throwable throwable) {
            result = handlerException(pjp, throwable);
        }

        return result;
    }

    private Object handlerException(ProceedingJoinPoint pjp, Throwable e) {
    	Object result = null;
        String msg = "Exception {方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage() + "}";
        logger.error(msg, e);
        result = Result.getInstance(ErrorCode.ERROR, msg);
        
        return result;
    }

	
}

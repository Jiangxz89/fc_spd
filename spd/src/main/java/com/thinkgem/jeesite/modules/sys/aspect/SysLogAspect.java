package com.thinkgem.jeesite.modules.sys.aspect;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import freemarker.template.utility.DateUtil;

@Aspect
@Component
public class SysLogAspect {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	 /**
     * 定义一个切入点
     */
    @Pointcut("execution(* com.thinkgem.jeesite.modules.hcy.service..*.*save*(..)) || execution(* com.thinkgem.jeesite.modules.hcy.service..*.*update*(..)) || execution(* com.thinkgem.jeesite.modules.hcy.service..*.*insert*(..))")
    private void pointCutMethod() {
    }

    /**
     *  声明前置通知
     */
    @Before("pointCutMethod()")
    public void doBefore(JoinPoint jp) {
        System.out.println("前置通知");
        String className = jp.getTarget().toString();  //获取对象
        String methodName = jp.getSignature().getName(); //获取方法
        System.out.println("------" + className + "------"+methodName+"------------");
    }

    /**
     *  声明后置通知
     * @param result
     */
    @AfterReturning(pointcut = "pointCutMethod()", returning = "result")
    public void doAfterReturning(JoinPoint jp, String result) {
        System.out.println("后置通知");
        System.out.println("========" + result + "========");
        String className = jp.getTarget().toString();  //获取对象
        String methodName = jp.getSignature().getName(); //获取方法
        System.out.println("========" + className + "========"+methodName+"========");
    }

    /**
     *  声明例外通知
     */
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        System.out.println("例外通知");
        System.out.println(e.getMessage());
    }

    /**
     *  声明最终通知
     */
    @After("pointCutMethod()")
    public void doAfter() {
        System.out.println("最终通知");
    }

    /**
     *  声明环绕通知
     * @param pjp
     * @return
     * @throws Throwable
     */
   /* @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("进入方法---环绕通知");
        Object o = pjp.proceed();
        User user = UserUtils.getUser();
        OperationLog log = new OperationLog();
        log.setMethodName(pjp.getTarget().toString()+"."+pjp.getSignature().getName());
        log.setParams(Arrays.toString(pjp.getArgs()));
        log.setOpertionUserId(user.getId());
        log.setOperationName(user.getName());
        log.setOperationMobile(user.getMobile());
        log.setOperationLoginName(user.getLoginName());
        log.setOperationTime(DateUtil.dateToStr(new Date(),12));
        mongoTemplate.save(log, "OperationLog");
        System.out.println("退出方法---环绕通知");
        return o;
    }*/
}
package com.example.lurenjiaspring.aop.advice;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import java.util.Arrays;

//FirstAspect 切面类
public class FirstAspect {

    /**
     * 前置通知:数据校验，身份校验
     * @param jp 连接点参数Joinpoint，必须放在方法的第一位，可对切入点进行分析
     *           比如拿连接点的名字，连接点的所在类，连接点的参数
     */
    public void before(JoinPoint jp){
        System.out.println("1...前置通知,切入类"+jp.getTarget().getClass());
        System.out.println("1...前置通知,切入方法"+jp.getSignature().getName());
        System.out.println("1...前置通知,参数："+ Arrays.toString(jp.getArgs()));
    }

    /**
     * 环绕通知:事务控制，权限控制，返回对象
     * @param pjp 对连接点的方法内容进行整体控制
     * @return 返回连接点的返回值
     * @throws Throwable 连接点有异常则抛出
     */
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("2...环绕前置， 在执行用辅助业务");
        Object obj = pjp.proceed(); //调用核心业务
        System.out.println("2...环绕返回对象："+obj.toString());
        System.out.println("2...环绕后置， 在执行用辅助业务");
        return obj;

    }

    /**
     * 后置通知:日志记录，返回对象
     * @param jp 连接点的基本信息
     * @param result 获取连接点的返回对象
     */
    public void afterReturning(JoinPoint jp,Object result){
        System.out.println("3...后置通知,切入类"+jp.getTarget().getClass());
        System.out.println("3...后置通知,切入方法"+jp.getSignature().getName());
        System.out.println("3...后置通知,返回对象为："+result);
    }

    /**
     * 最终通知:在方法最后执行
     * @param jp 连接点的基本信息
     */
    public void after(JoinPoint jp){
        System.out.println("4...最终通知,切入类"+jp.getTarget().getClass());
        System.out.println("4...最终通知,切入方法"+jp.getSignature().getName());
    }

    /**
     * 异常通知:异常处理，事务控制
     * 在目标方法抛出异常时执行的通知
     * @param jp 连接点的基本信息
     * @param e 连接点的异常信息
     */
    public void afterThrow(JoinPoint jp,Throwable e){
        System.out.println("5...异常通知,切入类"+jp.getTarget().getClass());
        System.out.println("5...异常通知,切入方法"+jp.getSignature().getName());
        System.out.println(e.getMessage());
    }
}


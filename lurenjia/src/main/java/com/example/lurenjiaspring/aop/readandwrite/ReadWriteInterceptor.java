//package com.example.lurenjiaspring.aop.readandwrite;
//
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.util.Objects;
//
//@Aspect
//@Order(Integer.MAX_VALUE - 2) //@1
//@Component
//public class ReadWriteInterceptor {
//
//    @Pointcut("target(IService)")
//    public void pointcut() {
//    }
//
//    //获取当前目标方法的最后一个参数
//    private Object getLastArgs(final ProceedingJoinPoint pjp) {
//        Object[] args = pjp.getArgs();
//        if (Objects.nonNull(args) && args.length > 0) {
//            return args[args.length - 1];
//        } else {
//            return null;
//        }
//    }
//
//    @Around("pointcut()")
//    public Object around(final ProceedingJoinPoint pjp) throws Throwable {
//        //判断是否是第一次进来,用于处理事务嵌套
//        boolean isFirst = false;
//        try {
//            if (DsTypeHolder.getDsType() == null) {
//                isFirst = true;
//            }
//            if (isFirst) {
//                Object lastArgs = getLastArgs(pjp);
//                if (DsType.SLAVE.equals(lastArgs)) {
//                    DsTypeHolder.slave();
//                } else {
//                    DsTypeHolder.master();
//                }
//            }
//            return pjp.proceed();
//        } finally {
//            //退出的时候，清理
//            if (isFirst) {
//                DsTypeHolder.clearDsType();
//            }
//        }
//    }
//}

package aspect.jdkproxy;


import aspect.jdkproxy.entity.HystrixCommand;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author charles.yao
 * @Description 实现InvocationHandler接口重写invoke方法, 自动生成一个代理类
 * @Date 2022/10/21 15:35
 */
@Slf4j
public class ProxyInvocationHandler implements InvocationHandler {
    //被代理得目标接口
    private Object target;

    public ProxyInvocationHandler() {
    }

    public ProxyInvocationHandler(Object target) {
        this.target = target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
    /**
     * 生成需要得动态代理类
     *
     * @return
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    /**
     * 处理代理的实例，并返回结果
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = null;
        try {
             invoke= method.invoke(target, args);
        } catch (Exception e) {
            HystrixCommand annotation = method.getAnnotation(HystrixCommand.class);
            if (annotation != null) {
                Class<?> declaringClass = method.getDeclaringClass();
                Method method1 = declaringClass.getMethod(annotation.fallbackMethod());
                method1.invoke(declaringClass);
                //Class<? extends Annotation> aClass = annotation.annotationType();
                //Method method1 = aClass.getMethod(annotation.fallbackMethod());
                //method1.invoke(aClass);
            }
            return null;
        }

        return invoke;
    }
}

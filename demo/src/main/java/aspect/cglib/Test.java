package aspect.cglib;

import org.springframework.cglib.proxy.Enhancer;


public class Test {
        public static void main(String[] args) {
            TargetAction targetAction = new TargetAction("demo1");
            CglibCallBackInvocationHandler handler = new CglibCallBackInvocationHandler(targetAction);
// aspect.cglib 方式一
            Enhancer enhancer = new Enhancer();
            //设置代理什么类
            enhancer.setSuperclass(targetAction.getClass());
            //设置invoker
            enhancer.setCallback(handler);

            TargetAction result = (TargetAction) enhancer.create();

            String proxyResult = result.doSomething();
//         proxy反射包下的 jdk动态代理
//            Proxy.newProxyInstance()
//            cglib下的没看懂
//            Proxy.newProxyInstance()

            System.out.println(proxyResult);


        }
}

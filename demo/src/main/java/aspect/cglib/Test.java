package aspect.cglib;

import org.springframework.cglib.proxy.Enhancer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Test {
        public static void main(String[] args) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add("25151");
            strings.add("277");
            strings.add("24564");
            List<String> collect = strings.stream().sorted().collect(Collectors.toList());
            System.out.println("collect = " + collect);


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

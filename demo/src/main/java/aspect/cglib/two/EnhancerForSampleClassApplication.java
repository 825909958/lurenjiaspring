package aspect.cglib.two;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * 引入接口实现远程调用原理可以是这个
 */
public class EnhancerForSampleClassApplication {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(A.class);
        enhancer.setCallback(new MxsmPoxy());
        A a = (A) enhancer.create();
        a.aaa("bbb");
    }

    public interface A {
        @RequestMapping("/a/b")
        public void aaa(String aaa);

    }

    public static class MxsmPoxy implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            RequestMapping annotation = method.getAnnotation(RequestMapping.class);
            String[] value = annotation.value();
            System.out.println(value[0]);
            return null;
        }
    }
}

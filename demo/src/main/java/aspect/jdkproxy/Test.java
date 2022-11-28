package jdkproxy;

import aspect.ParentInterface;
import aspect.TestHyCommand;

public class Test {

    /**
     * @Author charles.yao
     * @Description jdk动态代理测试
     * @Date 2022/10/21 15:44
     */
        public static void main(String[] args) throws Exception {
            //真实类
            TestHyCommand studentService = new TestHyCommand();

            //代理角色，不存在的,
            ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
            //设置要代理得对象
            proxyInvocationHandler.setTarget(studentService);
            //动态生成代理类
            ParentInterface iStudentService = (ParentInterface) proxyInvocationHandler.getProxy();

            iStudentService.a();
        }

    }

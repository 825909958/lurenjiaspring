package generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author THT
 * 泛型通配符
 * 简单总结下：
 * <p>
 * T 是一个 确定的 类型，通常用于泛型类和泛型方法的定义，
 * ？是一个 不确定 的类型，通常用于泛型方法的调用代码和形参，不能用于定义类和泛型方法。
 *  /*   ？和 T 都表示不确定的类型，区别在于我们可以对 T 进行操作，但是对 ？ 不行，比如如下这种 ：
 *     // 可以
 *     T t = operate();
 *     // 不可以
 *     ？ car = operate();//
 *
 * 2.如在代码中定义的List<object>和List<String>等类型，在编译后都会编程List，JVM看到的只是List。
 * 而由泛型附加的类型信息对JVM来说是不可见的。Java编译器会在编译时尽可能的发现可能出错的地方，
 * 但是仍然无法避免在运行时刻出现类型转换异常的情况。
 * 类型擦除也是Java的泛型实现方法与C++模版机制实现方式之间的重要区别。
 */
public class WildcardTest {

    @Test
    public void test1() {
        List<Dog> dogList = new ArrayList<>();
        test(dogList);
        //test1(dogList);
    }

    @Test
    /**
     * 10.3. 类型擦除与多态的冲突和解决方法
     * 这个其实是类型擦除引起的最大的问题了。
     * 然后再子类中重写参数类型为Integer的那两个方法，实现继承中的多态。
     *
     * 可是由于种种原因，虚拟机并不能将泛型类型变为Integer，只能将类型擦除掉，变为原始类型Object。
     * 这样，我们的本意是进行重写，实现多态。可是类型擦除后，只能变为了重载。这样，类型擦除就和多态有了冲突。
     * JVM知道你的本意吗？知道，可是它能直接实现吗，不能。如果真的不能的话，那我们怎么去重写我们想要的Integer类型参数的方法啊。
     *
     * JVM采用了一个特殊的方法，来完成这项功能，那就是桥方法。
     * 所以，虚拟机巧妙的使用了巧方法，来解决了类型擦除和多态的冲突。
     */
    public void test2() {

    }

    private  void test(List<? extends Animal> animals) {
        System.out.println("test输出:");
        for (Animal animal : animals) {
            System.out.print(animal.toString() + "-");
        }
    }

    private  void test1(List<Animal> animals) {
        System.out.println("test1输出:");
        for (Animal animal : animals) {
            System.out.print(animal.toString() + "-");
        }
    }

   private class Animal {
        @Override
        public String toString() {
            return "Animal";
        }
    }

   private class Dog extends Animal {
        @Override
        public String toString() {
            return "Dog";
        }
    }
}

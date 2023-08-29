package generic;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型擦除
 * @author THT
 */
public class GenericErase {
    @Test
    public void test1() {
        List<String> stringList = new ArrayList<String>();
        stringList.add("my");
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(123);
        System.out.println(stringList.getClass() == integerList.getClass());
    }

    @Test
    /**
     * 从上面的那个例2中，我们也可以明白List被擦除类型后，原始类型也变成了Object，所以通过反射我们就可以存储字符串了。
     */
    public void test01() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> list = new ArrayList<Integer>();
        //这样调用add方法只能存储整形，因为泛型类型的实例为Integer
        list.add(1);
        //这样写编译就会报错
        //        list.add("my");
        //通过反射的方式则可以存储String
        list.getClass().getMethod("add", Object.class).invoke(list, "my");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    /**
     * 2、如果类型变量有限定，那么原始类型就用第一个边界的类型变量来替换。
     * class Test_Generic1<T extends List & Collection>
     *
     *     1、这里我们传一个Collection的实现类Queue，也应该是可以的啊，但是为什么报错了呢？注意一点报错报的是编译错误，
     *     泛型提供编译前检测机制，也就是说在没运行前，泛型规定了多重限定时，在编译的时候取最小范围或共同子类。
     */
}

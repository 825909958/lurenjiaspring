package generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型测试
 * @author THT
 */
public class GenericTest<T> {

    private T key;
    //public GenericTest(T key) {
    //    this.key = key;
    //}
    /**
     * 1、这个虽然在方法中使用了泛型，但这并不是一个泛型方法。这只是类中一个普通的
     * 成员方法，只不过他的返回值是在声明泛型类已经声明过的泛型。所以在这个方法中才
     * 可以继续使用 T 这个泛型。
     */
    public T getKey() {
        return key;
    }

    @Test
    public void test1() {
        List<Object> objects = new ArrayList<>();
        objects.add("1");
        objects.add(1);
        System.out.println("objects = " + objects);
    }

    private  <T,V,Q,M,W,E> T genericMethod(V a) {
        return (T)a;
    }


    //这也不是一个泛型方法，这就是一个普通的方法，只是使用了Generic<Number>这个泛型类做形参而已。
    public void Method01(GenericTest<? extends Number> generic01) {
        System.out.println(generic01.getKey());
    }
    //这也不是一个泛型方法，这也是一个普通的方法，只不过使用了泛型通配符?
    //同时这也印证了泛型通配符章节所描述的，?是一种类型实参
    public void Method02(GenericTest<?> generic01) {
        System.out.println(generic01.getKey());
    }

    //必须是三个点
    public <T> void print(T... args) {
        for (T t : args) {
            System.out.println(t);
        }
    }

    public  void test() {
        //编译错误
        //List<String>[] ls = new ArrayList<String>[10];
    }
    @Test
    public  void test01() {
        //这样声明是正确的
        List<?>[] ls = new ArrayList<?>[10];
        ls[1] = new ArrayList<String>();
        //这样写编译就报错了
        //ls[1].add("1");
    }
    /**
     * 下面是sun官方文档里写的。其实不用太纠结，平时泛型虽然用的多，但也不会用的这么奇葩。
     */
    @Test
    public  void test02(){
        List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Correct.
        Integer i = (Integer) lsa[1].get(0); // OK
        System.out.println(i);
    }
    //正确
    @Test
    public  void test03() {
        List<String>[] ls = new ArrayList[10];
        ls[0] = new ArrayList<String>();
        ls[1] = new ArrayList<String>();
        ls[0].add("x");
    }

}

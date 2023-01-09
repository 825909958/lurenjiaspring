package mianshiti;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileDemo {
    @Test
    public void test1() throws IOException {
        // 只有BufferRead才有读取行的功能  相对路径的根目录是project的根文件夹
        FileReader fileReader = new FileReader("src/main/java/mianshiti/entity/a.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s = bufferedReader.readLine();
        System.out.println("s = " + s);
    }

    @Test
    /**
     * Class.getResourceAsStream(String path) ： path 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。
     *
     * Class.getClassLoader.getResourceAsStream(String path) ：默认则是从ClassPath根下获取，path不能以’/'开头，最终是由ClassLoader获取资源。
     *
     * ServletContext. getResourceAsStream(String path)：默认从WebAPP根目录下取资源，Tomcat下path是否以’/'开头无所谓，当然这和具体的容器实现有关。
     *
     * Jsp下的application内置对象就是上面的ServletContext的一种实现。
     */
    public void test2() throws IOException {
        // 相对这个class文件的路劲  前面有/就是根路劲   没有就是相对路劲

        //path 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从ClassPath根下获取。
        // 其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源
        //?????????????????????????????????????
        InputStream in = FileDemo.class.getResourceAsStream("");
        assert in != null;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
        System.out.println("bufferedInputStream.read() = " + bufferedInputStream.read());
        InputStreamReader inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);

        System.out.println("new InputStreamReader.read = " +
                + inputStreamReader.read());

    }

}

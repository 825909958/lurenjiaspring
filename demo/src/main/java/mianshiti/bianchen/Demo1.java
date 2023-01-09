package mianshiti.bianchen;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class Demo1 {
    /**
     * catch or finally 必须有一个
     */
    @Test
    public void test1() {
        int[] ints = new int[]{0,1,2,3,4,5};
        try {
            for (int i = 0; i < ints.length; i++) {
                try {
                    //if (ints[i] % 2 == 0) {
                    //    throw new NullPointerException();
                    //} else {
                    //    System.out.println(i);
                    //}
                    return;
                } finally {
                    System.out.println("e");
                }

            }
        } catch (Exception e) {
            System.out.println("E");
        }

    }

    @Test
    /**
     * 静态变量不应该在成员方法中定义
     */
    public void test2() {
        //static int i = 0;
    }

    @Test
    public void test3() {
        int i = 4 & 7;
        int i1 = 4 | 7;
        System.out.println("i = " + i);
        System.out.println("i1 = " + i1);
    }
    @Test
    public void test4() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println("1a");
                break;
            }
            System.out.println("2a");
        }
    }
    @Test
    public void test5() throws IOException {
        File file = new File("D:\\a.txt");
        boolean newFile = file.createNewFile();
    }


    @Test
    public void test6() throws Throwable {
        //int[][]  ints = new int[][];   error
        //int ints[10][10]= new int[][]; error
        // 数字一定在右边
        int ints[][] = new int[10][10];
        int []ints1[] = new int[10][10];
        int [][]ints2 = new int[10][10];
        String[] strings = new String[5];
        String string = strings[0];
        System.out.println("string = " + string);
        //Arrays.asList()
        //throw new Error();  true
        throw new Throwable();
    }


}

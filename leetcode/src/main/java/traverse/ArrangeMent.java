package traverse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author THT
 */
public class ArrangeMent {
    /**
     * @param list
     */
    public static int[][] arrangeMent(List<Integer> list, int count, int[][] data, int j) {
        if (count > list.size() - 1) {
            return data;
        }
        for (int i = 0; i < list.size(); i++) {
            arrangeMent(list, count + 1, data, j);
            /**
             * 分析方法 ： 把i如何遍历穷举
             * 相当于每次循环都执行了一次  1：9次0,1,2，  2：i变化了39次，外层循环也会执行
             */
            System.out.println("di递归执行完成");
            data[count][i] = i + 1;
        }
        return data;
    }


    public static void main(String[] args) {
//        Long a=21400000000L;
        List<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3));
        int size = integers.size();
        int[][] ints = arrangeMent(integers, 0, new int[size][size], 0);
    }

    static void printTwoArray(int[][] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length; j++) {
                System.out.print(ints[i][j]);
            }
            System.out.println();
        }
    }

}

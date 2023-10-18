package usuallyalgorithm.sort;

import java.util.Arrays;

public class Test {
    public static int[] VALUES = {7, 1, 3, 2, 9, 4,8,8,6,9,1,2};

    public static void main(String[] args) {
        //Sort sort = new InsertSort();
//        sort.sort(VALUES);
//        System.out.println(Arrays.toString(VALUES));

        // 有问题 希尔排序
        //Sort  sort = new HillSort();
        //sort.sort(VALUES);
        //System.out.println(Arrays.toString(VALUES));

        //// 冒泡排序
        //BubbleSort bubbleSort = new BubbleSort();
        //bubbleSort.sort(VALUES);
        //System.out.println(Arrays.toString(VALUES));

        // 快排
        QuickSort quickSort = new QuickSort();
        quickSort.sort(VALUES);
        System.out.println(Arrays.toString(VALUES));
    }
}

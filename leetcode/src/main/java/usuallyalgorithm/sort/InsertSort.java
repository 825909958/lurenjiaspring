package usuallyalgorithm.sort;

public class InsertSort implements Sort {
    public InsertSort() {
        System.out.print("插入排序");
    }

    /**
     * 每次插入都保证有序，所以插入每一个都去比较前面所有的,只要比前面的小就换位置
     *     {7, 1, 3, 2, 9, 4, 5}
     */
    @Override
    public int[] sort(int[] values) {
        for (int i = 1; i < values.length; i++) {
            int index = i;
            int temp = values[index];
            while (index > 0 && temp < values[index-1]) {
//                int swap = values[index];
                values[index] = values[index-1];
                values[index-1]=temp;
                index--;
            }
        }
        return values;
    }
}

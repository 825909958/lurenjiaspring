package traverse.sort;


public class HillSort implements Sort {

    public HillSort() {
        System.out.print("希尔排序");
    }

    // 7 1 3 2 9 4 5
    @Override
    public int[] sort(int[] values) {
  //      int temp = values.length;
  //      int templeft = values.length;
  //      int tempright = (temp - (temp / 2)) / 2;
  //      int i1 = temp - (temp / 2);
  //
  //      while (templeft >= 1 ) {
  //          for (int i = 0; i < (templeft / 2); i++) {
  //              int[] sort1 = insertSort.sort(new int[]{values[i], values[i + (templeft / 2)]});
  //              values[i] = sort1[0];
  //              values[i + templeft / 2] = sort1[1];
  //              int[] sort2 = insertSort.sort(new int[]{values[i1 + i], values[i1 + templeft / 2]});
  //              values[i1 + i] = sort2[0];
  //              values[i1 + i + templeft / 2] = sort2[1];
  //          }
  //          templeft = templeft / 2;
  //}
        int gap = values.length;
        while (true) {
            gap /= 2;
            //增量每次减半
            for (int i = 0; i < gap; i++) {
                //这个循环里其实就是一个插入排序
                for (int j = i + gap; j < values.length; j += gap) {
                    int k = j - gap;
                    while (k >= 0 && values[k] > values[k+gap]) {
                        int temp = values[k];
                        values[k] = values[k+gap];
                        values[k + gap] = temp;
                        k -= gap;
                    }
                }
            }
            if (gap == 1)
                break;
        }

        return values;
    }
}

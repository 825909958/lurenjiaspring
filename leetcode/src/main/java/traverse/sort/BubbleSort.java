package traverse.sort;

public class BubbleSort implements Sort {
    /**
     * 左右比较，只要比他大就换位置，o(n2)
     * @param values
     * @return
     */
    @Override
    public int[] sort(int[] values) {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length - i - 1; j++) {
                int temp = values[j];
                if (values[j] > values[j + 1]) {
                    values[j] = values[j + 1];
                    values[j + 1] = temp;
                }

            }
        }
        return values;
    }

}

import java.util.Arrays;

public class Sort {
    public static int[] bSort(int[] array) {
        int endFlag = array.length;
        for (int i = 0; i < endFlag; i++) {
            for (int j = 0; j < endFlag - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {5, 9, 2, 1};
        int[] result = bSort(array);
        Arrays.stream(result).forEach(System.out::println);
    }
}

package usuallyalgorithm.common;

public class FibonacciSequence {
    public static int f(int n) {
        int[] array=new int[n+1];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i <=n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

    public static int fDigui(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return f(n - 1) + f(n - 2);
    }

    public static void main(String[] args) {
        int n = 10;
        int f = f(n);
        System.out.println("f = " + f);
        int fDigui = fDigui(n);
        System.out.println("fDigui = " + fDigui);
    }
}

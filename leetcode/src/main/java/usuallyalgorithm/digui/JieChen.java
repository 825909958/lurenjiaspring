package usuallyalgorithm.digui;

public class JieChen {
    static String a = "name";
    public void printJieChen(int number) {
        for (; number > 0; number--) {
            int factorial = jieChen2(number);
            a = a + "1";
            System.out.print(factorial + "\t");
        }
    }

    public int jieChen(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return n * jieChen(n - 1);
    }

    public int jieChen2(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            sum = sum * i;
        }
        return sum;
    }

    public static void main(String[] args) {
        JieChen jieChen = new JieChen();
        jieChen.printJieChen(4);

    }
}

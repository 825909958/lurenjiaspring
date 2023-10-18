package usuallyalgorithm.usually;

public class JieCheng {
    public static int nJ(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int cellSum = 1;
            for (int j = 1; j <= i; j++) {
                cellSum *= j;
            }
            sum += cellSum;
        }
        return sum;
    }
    public static void main(String[] args) {
        int i = nJ(5);
        System.out.println("i = " + i);
    }
}

package usuallyalgorithm.common;

/**
 * @author THT 是否是回文数字
 */
public class Reversals {
    public static boolean isSameAfterReversals(int num) {
        return num == 0 || num % 10 != 0;
    }

    public static void main(String[] args) {
        boolean same = isSameAfterReversals(139);
        System.out.println("same = " + same);
    }
}

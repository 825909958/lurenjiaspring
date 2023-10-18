package usuallyalgorithm.common;

/**
 * @author THT
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

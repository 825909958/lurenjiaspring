package usuallyalgorithm.draw;

import org.junit.Test;

public class Star {
    public void printStar(int n) {
        if (n <= 0) {
            System.out.println("param is invalid");
        }
        int flag = 2 * n - 1;
        int mid = 0;
        for (int i = 1; i <= flag; i++) {
            if (i <= (flag / 2)+1) {
                for (int g = 1; g <= (flag - (2 * i)); g++) {
                    System.out.print(" ");
                }
                for (int x = 1; x <= 2*i-1; x++) {
                    System.out.print("* ");
                }
            }
            else {
                for (int j = 1; j <= 2*(i-((flag/2)+1))-1; j++) {
                    System.out.print(" ");
                }
                for (int k = 1; k <= (flag - (2 * (i-((flag/2)+1)))); k++) {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    public static void print(int n, int num) {

        // 第1种：简单正向
        for (int i = 0; i < n - 1; i++) {
            System.out.print(" ");
        }
        for (int j = num; j >= n; j--) {
            System.out.print("* ");
        }
        System.out.println("\n");
        if (n == 1) {
            return;
        }
        print(n - 1, num);
    }

    @Test
    public void printStar() {
        printStar(5);
        //print(5, 5);
    }
}

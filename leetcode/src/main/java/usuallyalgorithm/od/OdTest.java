package usuallyalgorithm.od;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 82590
 */
public class OdTest {
    public static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 遍历方式可以
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 二维数组大小
        int m = sc.nextInt();
        int n = sc.nextInt();

        // 控制台输入的矩阵集合
        int[][] arrs = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arrs[i][j] = sc.nextInt();
            }
        }

        // [0,0]位置初始化为1
        arrs[0][0] = 1;

        // 新的矩阵集合
        LinkedList<int[]> list = new LinkedList<>();

        list.add(new int[]{0, 0});

        // 1的个数
        int count = 1;

        // 每经过1秒，将上下左右值为0的元素同化为1
        while (list.size() > 0) {
            int[] pos = list.removeFirst();

            int x = pos[0];
            int y = pos[1];

            for (int i = 0; i < 4; i++) {
                int new_x = x + directions[i][0];
                int new_y = y + directions[i][1];
                System.out.println(new_x +"  "+   new_y);
                if (new_x >= 0 && new_x < m && new_y >= 0 && new_y < n && arrs[new_x][new_y] == 0) {
                    arrs[new_x][new_y] = 1;
                    count++;
                    list.add(new int[]{new_x, new_y});
                }
            }
        }

        // 输出非1的个数
        System.out.println(m * n - count);
    }
}


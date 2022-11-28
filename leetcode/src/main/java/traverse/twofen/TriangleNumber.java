package traverse.twofen;

import java.util.Arrays;

public class TriangleNumber {
    public static int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        // 若 a<=b<=c,则必有a+c>b, b+c>a,保证a+b>c即可
        int ret = 0;
        for (int i = 0; i < n-2; i++) {   // nums[i] == a
            for (int j = i + 1; j < n-1; j++) {   // nums[j] == b
                for ( int k = j+1; k < n; k++ ) {
                    if ( nums[k] < nums[i] + nums[j] ) {
                        ret++;
                    }
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int i = triangleNumber(new int[]{2, 2, 3, 4});
        System.out.println("i = " + i);
    }
}

package usuallyalgorithm.twofen;

import org.junit.Test;

public class FindTarget {

    int[] array = new int[]{-1, 1, 5, 9, 15, 16};

    public int findTarget(int[] nums, int target) {
        int begin = 0;
        int end = nums.length - 1;
        while (true) {
            int index = (begin + end) / 2;
            if (target == nums[index]) {
                return index;
            } else if (target > nums[index]) {
                if (begin == index) {
                    if (nums[begin] == target) {
                        return index;
                    } else if (nums[end] == target) {
                        return index + 1;
                    } else {
                        break;
                    }
                }
                begin = index;
            } else {
                if (end == index) {
                    if (nums[end] == target) {
                        return index;
                    } else if (nums[0] == target) {
                        return index - 1;
                    } else {
                        break;
                    }
                }
                end = index;
            }
        }
        return -1;
    }

    public int findTarget2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int index = (l + r) / 2;
            if (nums[index] == target) {
                return index;
            } else if (target < nums[index]) {
                r = index - 1;
            } else {
                l = index + 1;
            }
        }
        return -1;
    }

    @Test
    public void test1() {
        int target = findTarget(array, 16);
        System.out.println(target);

    }

    @Test
    public void test2() {
        int target = findTarget2(array, 5);
        System.out.println(target);
    }

}

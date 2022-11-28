package traverse.twofen;

public class FindTarget {
    static int findTarget(int[] nums, int target) {
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

    public static void main(String[] args) {
        int target = findTarget(new int[]{-1, 1, 5, 9, 15,16}, -10);
        System.out.println(target);
    }
}

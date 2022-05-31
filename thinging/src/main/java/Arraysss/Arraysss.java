package Arraysss;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2022/05/30 23:05
 */
public class Arraysss {
    /**
     * #704
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            // 中间值大于目标值 -----> 目标值落在了左半区间
            if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * #27
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int fast = 0;
        int slow;
        for (slow = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }


    /**
     * #26
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int fast = 1;
        int slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
                fast++;
            } else {
                fast++;
            }
        }
        return slow + 1;
    }

}

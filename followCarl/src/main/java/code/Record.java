package code;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/10/11 23:39
 */
public class Record {


    /**
     * Day 1
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int idx = (left + right) / 2;
            if (target == nums[idx]) {
                return idx;
            } else if (target < nums[idx]) {
                right = idx;
            } else if ( target >= nums[idx]) {
                left = idx + 1;
            }
        }
        return -1;
    }
}

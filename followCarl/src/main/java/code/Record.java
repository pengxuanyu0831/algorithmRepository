package code;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/10/11 23:39
 */
@Slf4j
public class Record {


    /**
     * Day 1
     * #704
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;
        while (left <= right) {
            int idx = (left + right) / 2 ;

            if (target < nums[idx]) {
                right = idx -1  ;
            } else if (target > nums[idx]) {
                left = idx + 1;
            } else {
                return idx;
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
        int left = 0;
        int right = 0;

        while (right < nums.length) {

            if (nums[right] != val) {

                nums[left] = nums[right];

                left++;
            }
            right++;
        }
        return left;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,2,3,0,4,2};
        int target =2 ;
        Record record = new Record();
        log.info("res >>>{} >>{}", record.removeElement(nums, target),nums);
    }
}

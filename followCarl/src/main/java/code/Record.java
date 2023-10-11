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


    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,3,5,9,12};
        int target = 9;
        Record record = new Record();
        log.info("res >>>{}", record.search(nums, target));
    }
}

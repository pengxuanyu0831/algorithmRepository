package code;

import com.pengxy.algorithm.AboutList.ListNode;
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


    /**
     * Day2 #977
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] copy = new int[nums.length];
        int k = nums.length-1;
        // 精髓：原式自乘之后，最大的数字一定在两边，然后两头比较，更大的放在新数组的右边
        while (left <= right) {
            if (nums[right] * nums[right] > nums[left] * nums[left]) {
                copy[k--] = nums[right] * nums[right];
                right--;
            } else {
                copy[k--] = nums[left] * nums[left];
                left++;
            }
        }
        return copy;
    }


    /**
     * #209
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int res = nums.length + 1;
        while (right < nums.length) {
            sum += nums[right];
            right++;
            while (sum >= target) {
                res = right - left > res ? res : right - left;
                // 滑动窗口的精髓：左边动的时候，要减掉左边的原值
                sum -= nums[left];
                left++;
            }
        }
        return res > nums.length ? 0 : res;
    }


    /**
     * Day 3 #203
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode node = new ListNode();
        // 保存头结点
        node.next = head;
        ListNode cur = node;

        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return node.next;
    }


    /**
     * #206
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head){
        if (head == null) {
            return head;
        }
        ListNode beg = null;
        ListNode mid = head;
        ListNode end = head.next;

        while (true) {
            mid.next = beg;
            if (end == null) {
                break;
            }

            beg = mid;
            mid = end;
            end = end.next;
        }

        head = mid;
        return head;
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        int target =2 ;
        Record record = new Record();
//        log.info("res >>>{} >>{}", record.sortedSquares(nums));
        log.info("res >>>{}", record.minSubArrayLen(11,nums));

    }
}

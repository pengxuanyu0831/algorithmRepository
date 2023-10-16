package code;

import com.pengxy.algorithm.AboutList.ListNode;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

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


    /**
     * Day4 #24
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 利用栈
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode node = new ListNode();
        ListNode temp = head;
        // 先保存头结点
        head = node;

        while (temp != null && temp.next !=null) {
            stack.add(temp);
            stack.add(temp.next);

            // 当前节点往前走两步，走到第二组要交换的节点
            temp = temp.next.next;

            node.next = stack.pop();
            node = node.next;
            node.next = stack.pop();
            node = node.next;

        }

        if (temp != null) {
            node.next = temp;
        } else {
            node.next = null;
        }

        return head.next;
    }


    /**
     * Day5 #242
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {

        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        for (char c : s.toCharArray()) {
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
        }

        for (char e : t.toCharArray()) {
            tMap.put(e, tMap.getOrDefault(e, 0));
        }

        return Objects.equals(sMap, tMap);
    }


    /**
     * #349
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> res = new HashSet<>();

        for (int i = 0; i < nums1.length; i++) {
            res.add(nums1[i]);
        }

        for (int k = 0; k < nums2.length; k++) {
            res.add(nums2[k]);
        }

        int[] resss = new int[res.size()];
        int sis = 0;
        for (Integer i : res) {
            resss[sis++] = i;
        }

        return resss;


    }


    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        int target =2 ;
        Record record = new Record();
//        log.info("res >>>{} >>{}", record.sortedSquares(nums));
        log.info("res >>>{}", record.minSubArrayLen(11,nums));

    }
}

package com.pengxy.algorithm.AboutList;

/**
 * @program algorithm
 * @description: 链表去重 快慢指针的思想
 * @author: pengxuanyu
 * @create: 2021/03/01 22:22
 */
public class deleteDuplicates1 {
    public ListNode deleteDuplicates1(ListNode head){
        if(head == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null){
            if(fast.val != slow.val){
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }
}

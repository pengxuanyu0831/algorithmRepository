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
                // 当快指针的值和慢指针的值不相同时（略过重复值，相当于删除），快慢指针指向的数值是不重复的
                // slow 的下一个指向fast
                // slow 所指的数值永远是正确的数值，让fast去试错
                slow.next = fast;
                // slow 去到fast的位置  slow++
                slow = slow.next;
            }
            fast = fast.next;
        }
        // 断开与后面重复元素的连接，起到删除重复元素的效果
        slow.next = null;
        return head;
    }
}

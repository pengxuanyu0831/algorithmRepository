package List;

import com.pengxy.algorithm.AboutList.ListNode;
import lombok.extern.slf4j.Slf4j;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2022/06/12 23:34
 */
@Slf4j
public class Listttt {

    /**
     * #203
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode temp = new ListNode();
        temp.next = head;
        ListNode cur = temp;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return temp.next;
    }


    /**
     * #707
     */
    class MyLinkedList {
        int size;

        ListNode header;

        public MyLinkedList() {
            size = 0;
            header = new ListNode(0);
        }

        MyLinkedList list = new MyLinkedList();

        public int get(int index) {
            if (index < 0 || index > list.size) {
                return -1;
            }
            ListNode node = header;
            for (int i = 0; i <= index; i++) {
                node = node.next;
            }
            return node.val;
        }

        public void addAtHead(int val) {
            this.addAtIndex(0, val);

        }

        public void addAtTail(int val) {
            this.addAtIndex(size, val);
        }

        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (index < 0) {
                index = 0;
            }
            // 需要一个虚拟头结点，所以长度+1
            size += 1;
            // 找位置
            ListNode pre = header;
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            // 创建要添加的节点
            ListNode node = new ListNode(val);
            node.next = pre.next;
            pre.next = node;
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            size -= 1;
            ListNode pre = header;
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            pre.next = pre.next.next;
        }
    }


    /**
     * 翻转链表  #206
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        // 创建一个临时节点
        ListNode temp = new ListNode();
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;

            pre = cur;
            cur = temp;
        }
        return pre;
        }
}

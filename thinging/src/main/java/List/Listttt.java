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
            // 这里之所以要建一个新的指针来指向头结点，是因为如果不新建一个临时指针，直接操作头结点的话，如果过程中头结点发生了变化，那么最终返回时，无法返回正确的头结点
            ListNode temp = header;
            for (int i = 0; i <= index; i++) {
                temp = temp.next;
            }
            return temp.val;
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
            // 这里要画图，先把待加入节点的.next指针指向目标位置的节点，再更新目标节点的上一个节点，指向新节点。否则目标节点会丢失，出现空指针
            node.next = pre.next;
            pre.next = node;
        }

        // 删除节点：临时指针temp，比如删除第index个节点，需要保证temp.next = index，临时节点的下一节点是需要删除的目标节点
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
        ListNode temp = new ListNode();
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            // 先保存cur的下一个
            temp = cur.next;
            // 保存完cur的下一个，就直接换下一个的指向
            cur.next = pre;
            // 双指针开始移动
            pre = cur;
            cur = temp;
        }
        return pre;
    }


    /**
     * #24
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode temp = new ListNode();
        temp.next = head;
        ListNode pre = temp;
        while (head != null && head.next != null) {
            ListNode node = head.next.next;
            pre.next = head.next;
            pre.next.next = head;
            pre.next.next.next = node;
            head = node;
            pre = pre.next.next;
        }
        return temp.next;
    }


    /**
     * #19
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 双指针
        ListNode slow = head;
        ListNode fast = head;
        while (n > 0) {
            if (fast.next != null) {
                fast = fast.next;
                n -= 1;
            }else{
                return head.next;
            }
        }
        // 需要再挪一位
        fast = fast.next;

        while (fast != null) {
            // fast 走一步，slow也走一步，当fast走到null时，slow位置为n的前一位
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }


    /**
     * #142
     * @param head
     * @return
     * 环:快慢指针
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // 当快慢指针相遇时，记录一个相遇点
            // 从相遇点 和 头结点再次一步一步走，则再次相遇时，就是环的入口
            if (fast == slow) {
                ListNode fast1 = fast;
                ListNode again = head;
                while (fast1 != again) {
                    fast1 = fast1.next;
                    again = again.next;
                }
                return again;
            }
        }
        return null;
    }


    /**
     * #2
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 结果链表，虚拟头结点
        ListNode dummyNode = new ListNode();
        //  临时指针指向新节点
        ListNode temp = dummyNode;
        // 进位标记
        int flag = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            int sum = x + y + flag;

            // 取余
            flag = sum / 10;

            // 取尾数
            sum = sum % 10;

            temp.next = new ListNode(sum);
            temp = temp.next;

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }

        }

        if (flag == 1) {
            temp.next = new ListNode(1);
        }
        return dummyNode.next;
    }


    /**
     * 面试 02-07
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode arrA = headA;
        ListNode arrB = headB;

        // 先算出链表的长度
        int lenA = 0;
        int lenB = 0;
        while (arrA != null) {
            lenA++;
            arrA = arrA.next;
        }

        while (arrB != null) {
            lenB++;
            arrB = arrB.next;
        }

        arrA = headA;
        arrB = headB;
        // 此时需要保证arrA是两个之间较长的那个链表
        if (lenB > lenA) {
            int tempLen = lenA;
            lenA = lenB;
            lenB = tempLen;

            ListNode tempNode = arrA;
            arrA = arrB;
            arrB = tempNode;
        }

        int gap = lenA - lenB;
        // 将两个链表的尾端对齐-->arrA 和 arrB 在同一起始
        // 这里解释一下，链表相交，意味着从某个节点之后，两个链表就【合并】成一个链表了，所以要尾端对齐，即假设尾端是同一链表，看是否有相交的指针。
        while (gap > 0) {
            arrA = arrA.next;
            gap--;
        }

        // 遍历arrA  arrB 当两个值相等时，就是有相交，否则没有
        while (arrA != null) {
            if (arrA == arrB) {
                return arrA;
            }
            arrA = arrA.next;
            arrB = arrB.next;
        }
        return null;

    }


    /**
     * #21
     * @param header1
     * @param header2
     * @return
     */
    public ListNode mergeTwoLists(ListNode header1, ListNode header2) {
        ListNode node = new ListNode(0);
        ListNode temp = node;
        while (header1 != null && header2 != null) {
            if (header1.val > header2.val) {
                // 保存小的那个节点
                temp.next = header2;
                temp = temp.next;
                header2 = header2.next;
            } else {
                temp.next = header1;
                temp = temp.next;
                header1 = header1.next;
            }
        }
        if (header1 == null) {
            temp.next = header2;
        } else {
            temp.next = header1;
        }
        return node.next;
    }


    /**
     * #876
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;

    }

}

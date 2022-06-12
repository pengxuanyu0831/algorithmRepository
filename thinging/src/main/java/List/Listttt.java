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
        ListNode pre = temp;
        ListNode cur = head;
        while (cur != null) {
            ListNode tempNode = cur.next;
            if (cur.val == val) {
                pre.next = tempNode;
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        return temp.next;

    }
}

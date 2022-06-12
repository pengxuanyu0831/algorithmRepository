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
}

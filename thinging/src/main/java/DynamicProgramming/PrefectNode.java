package DynamicProgramming;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2022/03/20 22:34
 */
public class PrefectNode {

    public int val;
    public PrefectNode left;
    public PrefectNode right;
    public PrefectNode next;

    public PrefectNode(int val) {
        this.val = val;
    }

    public PrefectNode() {
    }

    public PrefectNode(int val, PrefectNode left, PrefectNode right, PrefectNode next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }
}

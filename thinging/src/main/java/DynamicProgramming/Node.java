package DynamicProgramming;

import java.util.List;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2022/03/20 21:33
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int val) {
        val = this.val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}

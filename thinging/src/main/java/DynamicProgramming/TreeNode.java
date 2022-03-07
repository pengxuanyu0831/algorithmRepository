package DynamicProgramming;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2022/03/07 21:53
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode (int val ){
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

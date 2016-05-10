package p337Current;

/**
 * Created by kevint on 5/9/2016.
 * Solution: https://leetcode.com/discuss/91899/step-by-step-tackling-of-the-problem
 */
public class HouseRobberIII {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(2);

        System.out.println(rob(root1));

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(1);
        root2.left.left = new TreeNode(2);
        root2.left.left.left = new TreeNode(4);

        System.out.println(rob(root2));
    }

    public static int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int value = 0;

        if (root.left != null) {
            value += rob(root.left.left) + rob(root.left.right);
        }

        if (root.right != null) {
            value += rob(root.right.left) + rob(root.right.right);
        }

        return Math.max(value + root.val, rob(root.left) + rob(root.right));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode (int x) {
        val = x;
    }
}

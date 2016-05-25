package Trees;

/**
 * Created by kevint on 5/24/2016.
 */
public class BalancedBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
//        root.left.left = new TreeNode(4);
//        root.left.left.left = new TreeNode(5);

        System.out.println(isBalanced(root));
    }

    public static boolean isBalanced(TreeNode root) {
        if (checkBalance(root) == -1) {
            return false;
        }
        return true;
    }

    public static int checkBalance(TreeNode root) {
        // root has no children, so the height is 0
        if (root == null) {
            return 0;
        }

        // Get the height
        int leftHeight = checkBalance(root.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = checkBalance(root.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(checkBalance(root.right), checkBalance(root.left)) + 1;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

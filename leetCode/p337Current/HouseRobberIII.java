package p337Current;

/**
 * Created by kevint on 5/9/2016.
 * Solution: https://leetcode.com/discuss/91899/step-by-step-tackling-of-the-problem
 */
public class HouseRobberIII {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);

        System.out.println(rob(root));
    }

    public static int rob(TreeNode root) {
        int[] sumArr = new int[2];

        robHelper(root, 0, true, sumArr);
        robHelper(root, 0, false, sumArr);

        return Math.max(sumArr[0], sumArr[1]);
    }

    public static void robHelper(TreeNode node, int depth, boolean countEvens, int[] sumArr) {
        if (node == null) {
            return;
        }

        if (countEvens && depth % 2 == 0) {
            sumArr[0] += node.val;
        }
        else if (!countEvens && depth % 2 != 0) {
            sumArr[1] += node.val;
        }

        robHelper(node.right, depth + 1, countEvens, sumArr);
        robHelper(node.left, depth + 1, countEvens, sumArr);
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

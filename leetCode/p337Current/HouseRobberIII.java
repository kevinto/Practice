package p337Current;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kevint on 5/9/2016.
 * Solution: https://leetcode.com/discuss/91899/step-by-step-tackling-of-the-problem
 */
public class HouseRobberIII {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(2);

        System.out.println(rob3(root1));

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(1);
        root2.left.left = new TreeNode(2);
        root2.left.left.left = new TreeNode(4);

        System.out.println(rob3(root2));
    }

    // We can get the max value by either choosing the the current node value
    // plus the grandchildren's values OR getting the values from the current
    // nodes children.
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

        // (value + root.val)
            // - Means the max value we found by traversing the grandchildren
            //   of this current node plus the value of the current node.
        // (rob(root.left) + rob(root.right))
            // - The max value we can get from robbing the children nodes.
        return Math.max(value + root.val, rob(root.left) + rob(root.right));
    }

    public static int rob2(TreeNode root) {
        Map<TreeNode, Integer> map= new HashMap<>();
        return rob2Sub(root, map);
    }

    public static int rob2Sub (TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }

        int value = 0;
        if (root.left != null) {
            value += rob2Sub(root.left.left, map) + rob2Sub(root.left.right, map);
        }

        if (root.right != null) {
            value += rob2Sub(root.right.left, map) + rob2Sub(root.right.right, map);
        }

        int maxVal = Math.max(value + root.val, rob2Sub(root.left, map) + rob2Sub(root.right, map));
        map.put(root, maxVal);

        return maxVal;
    }

    public static int rob3(TreeNode root) {
        int[] res = robSub3(root);
        return Math.max(res[0], res[1]);
    }

    public static int[] robSub3(TreeNode root) {
        // The first element: If we robbed the children, instead of the current node.
        // The second element: If we robbed the root and the grandchildren, instead of the children
        if (root == null) {
            return new int[2];
        }

        int[] left = robSub3(root.left);
        int[] right = robSub3(root.right);

        int[] res = new int[2];

        // Here we didn't rob the current node. We figure out whether we have
        // a max of robbing the left or not robbing the left. Same for the right.
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        // Here we robbed the current, and we take the max values for not robbing
        // the children
        res[1] = root.val + left[0] + right[0];

        return res;
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

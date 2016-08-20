import java.util.*;
import java.util.LinkedList;

/**
 * Created by Kevin on 8/14/16.
 */
public class BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> newList = new ArrayList<>();
        newList.add(root.val);
        res.add(newList);

        while (!queue.isEmpty()) {
            int size = queue.size();
            newList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();

                if (curr.left != null) {
                    queue.add(curr.left);
                    newList.add(curr.left.val);
                }

                if (curr.right != null) {
                    queue.add(curr.right);
                    newList.add(curr.right.val);
                }
            }
            res.add(newList);
        }

        return res;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

import sun.awt.image.ImageWatched;

import java.util.*;
import java.util.LinkedList;

/**
 * Created by kevinto on 2/23/17.
 */
public class BinaryTreeVertOrderPrint {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(0);
        root.left.right.left = new TreeNode(5);


        root.right.right = new TreeNode(7);
        root.right.left = new TreeNode(1);
        root.right.left.right = new TreeNode(2);

        List<List<Integer>> res = verticalOrder(root);
        return;
    }

    private static HashMap<Integer, LinkedListNode> map;
    private static LinkedListNode head;
    private static LinkedListNode tail;

    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        map = new HashMap<>();
        head = new LinkedListNode(-1);
        tail = new LinkedListNode(-1);
        head.next = tail;
        tail.prev = head;
        verticalOrderHelper(root, 0, 0);

        traverseLLandAddToRes(res);

        return res;
    }

    private static void traverseLLandAddToRes(List<List<Integer>> res) {
        LinkedListNode curr = head.next;
        while (curr != tail) {
            res.add(new ArrayList<>(curr.children));
            curr = curr.next;
        }
    }

    private static void verticalOrderHelper(TreeNode root, int col, int prev) {
        if (root == null) {
            return;
        }

        if (!map.containsKey(col)) {
            LinkedListNode newLLNode = new LinkedListNode(col);
            if (col == prev) {
                head.next = newLLNode;
                tail.prev = newLLNode;
                newLLNode.prev = head;
                newLLNode.next = tail;
            } else if (col < prev) {
                newLLNode.next = head.next;
                head.next.prev = newLLNode;
                head.next = newLLNode;
                newLLNode.prev = head;
            } else { //(col > prev)
                newLLNode.next = tail;
                newLLNode.prev = tail.prev;
                tail.prev.next = newLLNode;
                tail.prev = newLLNode;
            }

            map.put(col, newLLNode);
        }
        map.get(col).children.add(root.val);

        prev = col;
        verticalOrderHelper(root.left, col - 1, prev);
        verticalOrderHelper(root.right, col + 1, prev);
    }

    private static class LinkedListNode {
        int key;
        LinkedListNode next;
        LinkedListNode prev
                ;
        List<Integer> children;

        LinkedListNode(int key) {
            this.key = key;
            children = new ArrayList<>();
        }
    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
}

/**
 * Created by kevinto on 11/21/16.
 */
public class SecondMaxBST {
    public static void main(String[] args) {
        // Returns 8
        Node root1 = new Node(5);
        root1.right = new Node(10);
        root1.right.left = new Node(8);
        System.out.println(getSecondMax(root1));

        // returns 5
        Node root2 = new Node(5);
        root2.right = new Node(10);
        System.out.println(getSecondMax(root2));

        // returns 10
        Node root3 = new Node(5);
        root3.right = new Node(10);
        root3.right.right = new Node(11);
        System.out.println(getSecondMax(root3));

        // returns 4
        Node root4 = new Node(5);
        root4.left = new Node(3);
        root4.left.right = new Node(4);
        System.out.println(getSecondMax(root4));
    }

    static Node prev = new Node(Integer.MIN_VALUE);
    private static int getSecondMax(Node root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return prev.val;
        } else if (root.right == null) {
            return getRightMost(root.left);
        }

        prev = root;
        return getSecondMax(root.right);
    }

    private static int getRightMost(Node root) {
        if (root == null) {
            return 0;
        } else if (root.right == null) {
            return root.val;
        }

        return getRightMost(root.right);
    }

    static class Node {
        int val;
        Node left;
        Node right;

        Node (int x) {
            val = x;
        }
    }
}

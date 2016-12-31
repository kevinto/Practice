/**
 * Created by kevinto on 12/30/16.
 */
public class CheckIfBst {
    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(5);

        root.left.left = new Node(4);
        root.left.right = new Node(6);

        System.out.println(isBstMinMax(root));
    }

    public static boolean isBstMinMax(Node root) {
        return isBstMinMax(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBstMinMax(Node root, int min, int max) {
        if (root == null) {
            return true;
        } else if (root.val < min || root.val > max) {
            return false;
        }

        boolean isBst = isBstMinMax(root.left, min, root.val);
        if (!isBst) {
            return isBst;
        }

        isBst = isBstMinMax(root.right, root.val, max);
        return isBst;
    }

    public static boolean isBstRecur(Node root) {
        Node[] prev = new Node[1];
        return isBstRecur(root, prev);
    }

    public static boolean isBstRecur(Node root, Node[] prev) {
        if (root == null) {
            return true;
        }

        boolean result = isBstRecur(root.left, prev);

        if (prev[0] != null && prev[0].val > root.val) {
            result = false;
        }
        prev[0] = root;

        result &= isBstRecur(root.right, prev);

        return result;
    }

    static class Node {
        Node left;
        Node right;
        int val;

        Node(int x) {
            this.val = x;
        }
    }
}

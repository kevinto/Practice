/**
 * Created by kevinto on 12/4/16.
 */
public class BinaryTreeSumLeftRightNode {
    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(3);
        root.left.left = new Node(1);
        root.left.right = new Node(2);

        root.right = new Node(7);
        root.right.left = new Node(6);
        root.right.right = new Node(1);
        System.out.println(isSum(root));
    }

    private static boolean isSum(Node root) {
        if (root == null) {
            return true;
        } else if (root.left == null && root.right == null) {
            return true;
        }

        int leftSum = root.left == null ? 0 : root.left.val;
        int rightSum = root.right == null ? 0 : root.right.val;
        if (root.val != leftSum + rightSum) {
            return false;
        }

        return isSum(root.left) && isSum(root.right);
    }


    static class Node {
        Node left;
        Node right;
        int val;

        Node (int x) {
            val = x;
        }
    }
}

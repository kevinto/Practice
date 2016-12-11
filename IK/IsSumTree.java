/**
 * Created by kevinto on 12/10/16.
 */
public class IsSumTree {
    public static void main(String[] args) {
        // Not a sum tree
        Node root1 = new Node(3);
        root1.left = new Node(2);
        root1.right = new Node(3);
        System.out.println("BruteForce: Is not a sum tree. should return false: " + isSumTreeBruteForce(root1));
        System.out.println("Optimal: Is not a sum tree. should return false: " + isSumTreeOptimal(root1));

        // Is a sum tree
        Node root2 = new Node(3);
        root2.left = new Node(2);
        root2.right = new Node(1);
        System.out.println("BruteForce: Is a sum tree. should return true: " + isSumTreeBruteForce(root2));
        System.out.println("Optimal: Is a sum tree. should return true: " + isSumTreeOptimal(root2));
    }

    public static boolean isSumTreeOptimal(Node root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        int leftSum = getSumOptimal(root.left);
        int rightSum = getSumOptimal(root.right);
        if (leftSum + rightSum != root.val) {
            return false;
        }

        boolean isSum = isSumTreeOptimal(root.left);
        isSum &= isSumTreeOptimal(root.right);

        return isSum;
    }

    private static int getSumOptimal(Node root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return root.val;
        } else {
            return root.val * 2;
        }
    }

    public static boolean isSumTreeBruteForce(Node root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        boolean isSum = root.val == getChildrenSums(root);
        isSum &= isSumTreeBruteForce(root.left);
        isSum &= isSumTreeBruteForce(root.right);

        return isSum;
    }

    public static int getChildrenSums(Node root) {
        if (root == null) {
            return 0;
        }

        int leftSum = root.left == null ? 0 : root.left.val + getChildrenSums(root.left);
        int rightSum = root.right == null ? 0 : root.right.val + getChildrenSums(root.right);

        return leftSum + rightSum;
    }

    private static class Node {
        Node left;
        Node right;
        int val;

        Node(int x) {
            val = x;
        }
    }
}

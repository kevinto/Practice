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

    // Optimal is an evolution of the brute force. With the insight that
    // you can get the sum of a subtree by doubling the root of that subtree
    // You can only use this property when you are sure that the subtree
    // is valid. This recursion goes down the to bottom of the tree and
    // checks the validity in a post order method to make sure both
    // subtrees are valid until you can use the property on the current root node.
    // The critical question here is: What do you do if you know the left tree
    // and the right tree are valid? How can you validate that the current
    // value is correct by using this?
    public static boolean isSumTreeOptimal(Node root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        // Does a check for root, immediate left, and immediate right to check validity.
        int leftSum = getSumOptimal(root.left);
        int rightSum = getSumOptimal(root.right);
        if (leftSum + rightSum != root.val) {
            return false;
        }

        // After we check root, recursively check the shildren
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

        boolean isSum = root.val == getChildrenSums(root.left) + getChildrenSums(root.right);
        isSum &= isSumTreeBruteForce(root.left);
        isSum &= isSumTreeBruteForce(root.right);

        return isSum;
    }

    public static int getChildrenSums(Node root) {
        if (root == null) {
            return 0;
        }

        int leftSum = getChildrenSums(root.left);
        int rightSum = getChildrenSums(root.right);

        // Alt implementation that is less clean. The current implementation says given the root
        // find the sum of all nodes in tree.
//        int leftSum = root.left == null ? 0 : root.left.val + getChildrenSums(root.left);
//        int rightSum = root.right == null ? 0 : root.right.val + getChildrenSums(root.right);

        return leftSum + rightSum + root.val;
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

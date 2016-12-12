/**
 * Created by kevinto on 12/12/16.
 *
 * Tree is sorted by low values
 */
public class IntervalTree {
    public static void main(String[] args) {

    }

    private static Node TreeRoot;

    public static void initTree() {
        TreeRoot = null;
    }

    public static void add(int newLow, int newHigh) {
        if (TreeRoot == null) {
            TreeRoot = new Node(newLow, newHigh, newHigh);
            return;
        }



        // Base case: Tree is empty, new node becomes TreeRoot

        // Get low value of interval at TreeRoot

        // If TreeRoot's low value is smaller, then new interval goes to
        // left subtree

            // Else, new node goes to right subtree.

        // Update the max value of this ancestor if needed
    }

    private static Node insert(Node root, int newLow, int newHigh) {
        if (root == null) {
            return new Node(newLow, newHigh, newHigh);
        }

        if (newLow < root.low) {
            root.left = insert(root.left, newLow, newHigh);
        } else if (newLow > root.low) {
            root.right = insert(root.right, newLow, newHigh);
        }

        // Set maxes
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;
        if (root.left != null) {
            leftMax = root.left.max;
        }
        if (root.right != null) {
            rightMax = root.right.max;
        }
        root.max = Math.max(root.max, Math.max(leftMax, rightMax));

        return root;
    }

    private static class Node {
        Node left;
        Node right;
        int max;
        int low;
        int high;

        Node(int l, int h, int m) {
            this.low = l;
            this.high = h;
            this.max = m;
        }
    }
}

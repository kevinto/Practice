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

        insert(TreeRoot, newLow, newHigh);
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

        // Set maxes - You only need to set maxes on the way back up.
        // The maxes won't change unless the new node has a greater max.
        if (root.max < newHigh) {
            root.max = newHigh;
        }

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

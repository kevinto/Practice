/**
 * Created by kevinto on 12/12/16.
 *
 * Tree is sorted by low values
 */
public class IntervalTree {
    public static void main(String[] args) {
        Node root1 = new Node(5,0, 0);
        root1.left = new Node(2, 0, 0);
        root1.left.left = new Node(1,0,0);
        root1.left.right = new Node(10,0,0);

        Node result = getMinIterative(root1);
        return;
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

    public static void delete(int deleteLow, int deleteHigh) {
        TreeRoot = remove(TreeRoot, deleteLow, deleteHigh);
    }

    private static Node remove(Node root, int deleteLow, int deleteHigh) {
        if (root == null) {
            return null;
        }

        if (root.low < deleteLow) {
            root.right =remove(root.right, deleteLow, deleteHigh);
        } else if (root.low > deleteLow) {
            root.left = remove(root.left, deleteLow, deleteHigh);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            Node minNodeRightSubtree = getMinIterative(root.right);
            root.low = minNodeRightSubtree.low;
            root.high = minNodeRightSubtree.high;
//            root.max = ??
            // TODO think about the resetting maxes
            root.right = remove(root.right, minNodeRightSubtree.low, minNodeRightSubtree.high);
        }

        return root;
    }

    private static Node getMinRecursive(Node root) {
        if (root == null) {
            return null;
        } else if (root.left == null) {
            return root;
        }

        return getMinRecursive(root.left);
    }

    private static Node getMinIterative(Node root) {
        Node prev = null;
        Node curr = root;

        while (curr != null) {
            prev = curr;
            curr = curr.left;
        }
        return prev;
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

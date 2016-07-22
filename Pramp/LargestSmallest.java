/**
 * Created by Kevin on 7/22/16.
 * Find the largest smallest value for a given int x in a BST
 */
public class LargestSmallest {
    public static void main(String[] args) {
        NodeLS root = new NodeLS(14);
        NodeLS rootLeft = new NodeLS(9);
        NodeLS rootRight = new NodeLS(15);
        root.left = rootLeft;
        root.right = rootRight;

        rootLeft.left = new NodeLS(5);
        rootLeft.left.left = new NodeLS(1);

        rootLeft.right = new NodeLS(10);
        rootLeft.right.right = new NodeLS(12);

        try {
            System.out.println("14: " + findLargestSmallest(root, 14));
            System.out.println("9: " + findLargestSmallest(root, 9));
            System.out.println("1: " + findLargestSmallest(root, 1));
            System.out.println("15: " + findLargestSmallest(root, 15));
            System.out.println("13: " + findLargestSmallest(root, 13));
            System.out.println("Null: " + findLargestSmallest(null, 13));
        }
        catch(NoSmallLargeFoundException ex) {
            System.out.println("Ex was thrown.");
        }

    }

    private static int findLargestSmallest(NodeLS root, int x) throws NoSmallLargeFoundException {
        if (root == null) throw new NoSmallLargeFoundException("None");

        int largestSmallest = 0;
        NodeLS curr = root;
        while (curr != null) {
            if (curr.key < x) {
                largestSmallest = curr.key;
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }

        return largestSmallest;
    }
}

class NodeLS {
    NodeLS left;
    NodeLS right;
    int key;

    NodeLS (int x) {
        this.key = x;
    }
}

class NoSmallLargeFoundException extends Exception {
    NoSmallLargeFoundException(String message) {
        super(message);
    }
}

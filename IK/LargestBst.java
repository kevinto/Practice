/**
 * Created by kevinto on 12/3/16.
 */
public class LargestBst {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(100);
        root.right = new Node(3);

        root.left.left = new Node(50);
        root.left.right = new Node(200);
        root.left.right.left = new Node(1);
        root.left.right.right = new Node(3);

        root.right.left = new Node(1);
        root.right.right = new Node(100);
        System.out.println(findLargestBST(root));
    }

    private static int largestBst;
    static int findLargestBSTThatIncludesSomeAllDesc(Node n) {
        largestBst = Integer.MIN_VALUE;

        findLargestBSTThatIncludesSomeAllDesc(n, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return largestBst;
    }

    // This solution is for the may or may not include all the descendants
    static int findLargestBSTThatIncludesSomeAllDesc(Node n, int min, int max) {
        if (n == null) {
            return 0;
        } else if (n.val > min && n.val < max) {
            int leftCount = findLargestBSTThatIncludesSomeAllDesc(n.left, min, n.val);
            int rightCount = findLargestBSTThatIncludesSomeAllDesc(n.right, n.val, max);

            int bstSize = 1 + leftCount + rightCount;
            if (bstSize > largestBst) {
                largestBst = bstSize;
            }
            return bstSize;
        } else {
            findLargestBSTThatIncludesSomeAllDesc(n, Integer.MIN_VALUE, Integer.MAX_VALUE);

            return 0;
        }
    }

    public static int findLargestBST(Node root) {
        int[] min = new int[1];
        int[] max = new int[1];
        int[] maxBSTSize = {0};
        boolean[] isBST = {false};

        findSizeOfLargestBSTAllDescRequired(root, min, max, isBST, maxBSTSize);
        return maxBSTSize[0];
    }

    // Return value is the size of the largest all-descendants BST.
    // Implementation notes:
        // Doing a pre-order traversal. Visit left. Check if curr is greater than left's max.
        // Maintain min and max found so far. Visit right. Check if curr is less then right's min.
        // Maintain min and max found so far. Check if left and right are bst based on the recursive call's results.
    private static int findSizeOfLargestBSTAllDescRequired(Node currentNode, int[] min, int[] max, boolean[] isBST, int[] maxBSTSize)
    {
        // Gets reset on every recursive call. We want to reset because we
        // don't want to mix with min and max from different subtrees.
        min[0] = Integer.MAX_VALUE;
        max[0] = Integer.MIN_VALUE;

        if (currentNode == null)
        {
            isBST[0] = true;
            return 0;
        }

        // In this call, min[0] and max[0] would be updated
        // isBST[0] would be updated if left sub-tree is BST
        int leftTreeSize = findSizeOfLargestBSTAllDescRequired(currentNode.left, min, max, isBST, maxBSTSize);

        // Check if left sub-tree is a BST and no node in left sub-tree is greater than current node
        // Check against left's max
        boolean isLeftValid = isBST[0] && (max[0] < currentNode.val);

        // Before updating min[0] and max[0] in right sub-tree save min and max values seen so far
        // Saves min and max from left subtree with the current node.
        int tempMin = Math.min(currentNode.val, min[0]);
        int tempMax = Math.max(currentNode.val, max[0]);

        // In this call, min[0] and max[0] would be updated
        // isBST[0] would be updated if right sub-tree is BST
        int rightTreeSize = findSizeOfLargestBSTAllDescRequired(currentNode.right, min, max, isBST, maxBSTSize);

        // Check if right sub-tree is a BST and no node in right sub-tree is less than current node
        // Check against right's min
        boolean isRightValid = isBST[0] && (currentNode.val < min[0]);

        // Before returning update min[0] which would be the minimum value seen in this sub-tree with root as currentNode
        // and update max[0] which would be the maximum value seen in this sub-tree before
        // Saves min and max from right subtree with the current node.
        min[0] = Math.min(tempMin, min[0]);
        max[0] = Math.max(tempMax, max[0]);

        // If this tree with root as currentNode is a valid BST
        if (isLeftValid && isRightValid)
        {
            // This sub-tree at currentNode is also a BST
            isBST[0] = true;

            // Update max BST size accordingly
            if ((1 + leftTreeSize + rightTreeSize) > maxBSTSize[0])
            {
                maxBSTSize[0] = (1 + leftTreeSize + rightTreeSize);
            }

            return (1 + leftTreeSize + rightTreeSize);
        }

        // if this tree is not BST, we don't really use the value returned by it. Return -1.
        isBST[0] = false;
        return -1;
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

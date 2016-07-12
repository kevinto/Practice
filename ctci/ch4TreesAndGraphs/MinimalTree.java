package ch4TreesAndGraphs;

/**
 * Created by Kevin on 5/13/16.
 *
 * Problem 4.2: Given a sorted (increasing order) array with unique integer
 *              elements, write an algorithm to create a BST with min height.
 *
 *  What is the difference between height and depth?
 */
public class MinimalTree {
    public static void main(String[] args) {
        int[] testArr1 = { 1, 2, 3, 4, 5, 6 };
        NodeBST root = minBstTree(0, testArr1.length - 1, testArr1);
        printTree(root);

        return;
    }

    public static NodeBST minBstTree(int start, int end, int[] arr) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        NodeBST root = new NodeBST(arr[mid]);

        root.left = minBstTree(start, mid - 1, arr);
        root.right = minBstTree(mid + 1, end, arr);

        return root;
    }

    public static void printTree(NodeBST root) {
        if (root == null) {
            return;
        }

        printTree(root.left);
        System.out.println(root.val);
        printTree(root.right);
    }
}

class NodeBST {
    int val;
    NodeBST left;
    NodeBST right;

    NodeBST(int x) {
        this.val = x;
    }
}

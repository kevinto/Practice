package Trees;

import java.util.List;

/**
 * Created by kevint on 5/25/2016.
 *
 * Problem: Given an array where elements are sorted in
 *          ascending order, convert it to a height balanced BST.
 */
public class SortedArrayToBalancedTree {
    public static void main(String[] args) {

    }

    public static TreeNode sortedArrayToBST(final List<Integer> a) {
        // Convert list to array
        int[] arr = new int[a.size()];
        for (int i = 0; i < a.size(); i++) {
            arr[i] = a.get(i);
        }

        return genTree(arr, 0, arr.length - 1);
    }

    public static TreeNode genTree(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.right = genTree(arr, start, mid - 1);
        root.left = genTree(arr, mid + 1, end);
        return root;
    }
}

package p235;

import java.util.Collections;
import java.util.Vector;

/**
 * Created by Kevin on 4/4/16.
 * Link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * Problem: Given a binary search tree (BST), find the lowest common ancestor (LCA)
 *          of two given nodes in the BST.
 *
 *                 _______6______
 *                 /              \
 *             ___2__          ___8__
 *            /      \        /      \
 *           0      _4       7       9
 *                /  \
 *               3   5
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is
 * defined between two nodes v and w as the lowest node in T that has both v and w
 * as descendants (where we allow a node to be a descendant of itself).”
 *
 * 1. Do i need to optimize for space or time?
 *
 * Brainstorm solutions:
 *      - Track the paths to both nodes. See if there are any common ancestors. Return
 *        the lowest common ancestor.
 *      -
 *
 */

public class LcaOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node8 = new TreeNode(8);
        TreeNode node0 = new TreeNode(0);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);

        root.left = node2;
        root.right = node8;

        node2.left = node0;
        node2.right = node4;
        node4.left = node3;
        node4.right = node5;

        node8.left = node7;
        node8.right = node9;

        System.out.println("lca of 5,7 is " + lowestCommonAncestor(root, node5, node7).value);
        System.out.println("lca of 2,8 is " + lowestCommonAncestor(root, node2, node8).value);
        System.out.println("lca of 2,4 is " + lowestCommonAncestor(root, node2, node4).value);
        System.out.println("lca of 3,5 is " + lowestCommonAncestor(root, node3, node5).value);

        // TODO: fix bug. proposed here : https://leetcode.com/discuss/81725/lca-of-3-5-in-the-given-example-is-2-right
    }

//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Vector<Integer> v1 = new Vector<>();
        Vector<Integer> v2 = new Vector<>();

        binarySearch(root, p, v1);
        binarySearch(root, q, v2);

        int lcaValue = -1;
        for (int i = v1.size() - 1; i >= 0; i--) {
            for (int j = v2.size() - 1; j >= 0; j--) {
                int v1Value = v1.get(i);
                int v2Value = v2.get(j);
                if ( v1Value == v2Value ) {
                    lcaValue = v1.get(i);
                    break;
                }
            }

            if (lcaValue != -1) {
                break;
            }
        }

        TreeNode objNode = new TreeNode(lcaValue);

        return binarySearch(root, objNode, v1);
    }

    public static TreeNode binarySearch(TreeNode root, TreeNode objNode, Vector<Integer> path) {
        if (root == null) {
            return null;
        }

        path.add(root.value);
        if (root.value == objNode.value) {
            return root;
        }

        if (objNode.value > root.value) {
            return binarySearch(root.right, objNode, path);
        }
        else {
            return binarySearch(root.left, objNode, path);
        }
    }
}

class TreeNode {
    public int value;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { value = x; }
}

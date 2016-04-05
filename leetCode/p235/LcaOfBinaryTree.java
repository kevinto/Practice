package p235;

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
        BinaryTreeNode root = new BinaryTreeNode(6);
        BinaryTreeNode node2 = new BinaryTreeNode(2);
        BinaryTreeNode node8 = new BinaryTreeNode(8);
        BinaryTreeNode node0 = new BinaryTreeNode(0);
        BinaryTreeNode node4 = new BinaryTreeNode(4);
        BinaryTreeNode node7 = new BinaryTreeNode(7);
        BinaryTreeNode node9 = new BinaryTreeNode(9);
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node5 = new BinaryTreeNode(5);

        root.left = node2;
        root.right = node8;

        node2.left = node0;
        node2.right = node4;
        node4.left = node3;
        node4.right = node5;

        node8.left = node7;
        node8.right = node9;

        System.out.println(findLca(root, 2, 8));
    }

    private static int findLca(BinaryTreeNode root, int firstNode, int secondNode) {
        // Track path to first node.
            // Do a binary search

        // Track path to second node.

        return 0;
    }

    public static void binarySearch(BinaryTreeNode root, int start, int end) {

    }
}

class BinaryTreeNode {
    private int value;
    BinaryTreeNode left;
    BinaryTreeNode right;
    BinaryTreeNode(int x) { value = x; }
}

/**
 * Created by kevint on 4/7/2016.
 *
 * Sample Tree:
 *                  _______6______
 *                 /              \
 *             ___2__          ___8__
 *            /      \        /      \
 *           0      _4       7       9
 *                /  \
 *               3   5
 * 1. searching - find a node
 * 2. traversal - how to visit all nodes
 * 3. insertion - insert a new node
 * 4. deletion - delete a node
 */
public class BinaryTreeClass {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(6);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(8);

        BinaryTreeNode node2 = root.left;
        node2.left = new BinaryTreeNode(0);
        node2.right = new BinaryTreeNode(4);

        BinaryTreeNode node8 = root.right;
        node8.left = new BinaryTreeNode(7);
        node8.right = new BinaryTreeNode(9);

        BinaryTreeNode node4 = node2.right;
        node4.left = new BinaryTreeNode(3);
        node4.right = new BinaryTreeNode(5);

        // Search test
        System.out.println("Search for left node passed: " + (searchBinaryTree(root, 3).value == 3));
        System.out.println("Search for right node passed: " + (searchBinaryTree(root, 9).value == 9));

        // TODO: Think about balanced binary trees
        // Add test
        System.out.println("Add 10 passed: " + addNode(root, 10));

        // Delete test
        System.out.println("Add 10 passed: " + deleteNode(root, 10));
    }

    private static BinaryTreeNode searchBinaryTree(BinaryTreeNode root, int value) {
        if (root == null) {
            return null;
        }

        if (root.value == value) {
            return root;
        }

        if (value > root.value) {
            return searchBinaryTree(root.right, value);
        }
        else {
            return searchBinaryTree(root.left, value);
        }
    }

    private static boolean addNode(BinaryTreeNode root, int value) {
        if (root == null) {
            return false;
        }

        if (root.value == value) {
            return false;
        }
        else if (value > root.value) {
            if (root.right == null) {
                root.right = new BinaryTreeNode(value);
                return true;
            }
            else {
                return addNode(root.right,value);
            }
        }
        else if (value < root.value) {
            if (root.left == null) {
                root.left = new BinaryTreeNode(value);
                return true;
            }
            else {
                return addNode(root.left,value);
            }
        }

        return false;
    }

    public static boolean deleteNode(BinaryTreeNode root, int value) {
        // find the node before the node we want to delete
        // point that node to the node after the node we want to delete
        return false;
    }
}

class BinaryTreeNode {
    public int value;
    BinaryTreeNode left;
    BinaryTreeNode right;
    BinaryTreeNode(int x) { value = x; }
}

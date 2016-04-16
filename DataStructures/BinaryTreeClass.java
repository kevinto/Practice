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
        insertNode(root, 2);
        insertNode(root, 8);
        insertNode(root, 0);
        insertNode(root, 4);
        insertNode(root, 7);
        insertNode(root, 9);
        insertNode(root, 3);
        BinaryTreeNode nodeForSuccessor = insertNode(root, 5);

        // Search test
        System.out.println("Search for left node passed: " + (searchBinaryTree(root, 3).value == 3));
        System.out.println("Search for right node passed: " + (searchBinaryTree(root, 9).value == 9));

        // TODO: Think about balanced binary trees
        // Add test
        System.out.println("Add 10 passed: " + insertNode(root, 10));
        printBinaryTree(root);
        System.out.println("");

        System.out.println("Delete 10 passed: " + deleteKey(root, 10).value);
        printBinaryTree(root);
        System.out.println("");

        System.out.println("Min value:  " + minValue(root));

        System.out.println("Successor for node 5:  " + getSuccessor(nodeForSuccessor).value);
    }

    // TODO: I need to pass in a node not a value
    private static BinaryTreeNode getSuccessor(BinaryTreeNode node) {
        // if right is not null get the min of the right
        if (node.right != null) {
            return minNode(node.right);
        }

        // if right is null, return the lowest ancestor of x
        // whose left child is also an ancestor of x. if we pick
        // 5 from above the successor is 6 because 6 is an
        // ancestor whose left child, 2, is an ancestor of 5.
        BinaryTreeNode lowestAncestor = node.parent;
        while (lowestAncestor != null && node == lowestAncestor.right) {
            node = lowestAncestor;
            lowestAncestor = lowestAncestor.parent;
        }

        return lowestAncestor;
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

    private static BinaryTreeNode insertNode(BinaryTreeNode root, int value) {
        if (root == null) {
            return null;
        }

        if (root.value == value) {
            return root;
        }
        else if (value > root.value) {
            if (root.right == null) {
                root.right = new BinaryTreeNode(value);
                root.right.parent = root;
                return root.right;
            }
            else {
                return insertNode(root.right,value);
            }
        }
        else if (value < root.value) {
            if (root.left == null) {
                root.left = new BinaryTreeNode(value);
                root.left.parent = root;
                return root.left;
            }
            else {
                return insertNode(root.left,value);
            }
        }

        return root;
    }

    private static BinaryTreeNode deleteKey(BinaryTreeNode root, int key) {
        return root = deleteNode(root, key);
    }

    private static BinaryTreeNode deleteNode(BinaryTreeNode root, int value) {
        if (root == null) return root;

        else if (value < root.value) {
            root.left = deleteNode(root.left, value);
        }
        else if (value > root.value) {
            root.right = deleteNode(root.right, value);
        }
        else {
            if (root.left == null) {
                // Why return right node here? What if its null?
                return root.right;
            }
            else if (root.right == null) {
                // Why return left node here? what if its null?
                return root.left;
            }
            else {
                root.value = minValue(root.right);
                root.right = deleteNode(root.right, root.value);
            }
        }
        return root;
    }

    private static int minValue(BinaryTreeNode root) {
        int minValue = root.value;
        while (root != null) {
            minValue = root.value;
            root = root.left;
        }

        return minValue;
    }

    private static BinaryTreeNode minNode(BinaryTreeNode root) {
        BinaryTreeNode minNode = root;
        while (root != null) {
            minNode = root;
            root = root.left;
        }

        return minNode;
    }

    private static void printBinaryTree(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        printBinaryTree(root.left);
        System.out.print(root.value + " ");
        printBinaryTree(root.right);
    }
}

class BinaryTreeNode {
    public int value;
    BinaryTreeNode parent;
    BinaryTreeNode left;
    BinaryTreeNode right;
    BinaryTreeNode(int x) { value = x; }
}

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
        insertNode(root, 5);

        // Search test
        System.out.println("Search for left node passed: " + (searchBinaryTree(root, 3).value == 3));
        System.out.println("Search for right node passed: " + (searchBinaryTree(root, 9).value == 9));

        // TODO: Think about balanced binary trees
        // Add test
        System.out.println("Add 10 passed: " + insertNode(root, 10));
        printBinaryTree(root);
        System.out.println("");

        // TODO: Bug here: Delete test
        System.out.println("Delete 10 passed: " + deleteKey(root, 10).value);
        printBinaryTree(root);
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

    private static boolean insertNode(BinaryTreeNode root, int value) {
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
                return insertNode(root.right,value);
            }
        }
        else if (value < root.value) {
            if (root.left == null) {
                root.left = new BinaryTreeNode(value);
                return true;
            }
            else {
                return insertNode(root.left,value);
            }
        }

        return false;
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
        while (root.left != null) {
            minValue = root.value;
            root = root.left;
        }

        return minValue;
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
    BinaryTreeNode left;
    BinaryTreeNode right;
    BinaryTreeNode(int x) { value = x; }
}

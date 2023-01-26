/**
 * Created by kevinto on 12/13/16.
 */
public class InOrderPreOrderTreeContruction {
    public static void main(String[] args) {
        int[] inOrder = {2,5,6,10,12,14,15};
        int[] preOrder = {10,5,2,6,14,12,15};
        Node root = constructTree(inOrder, preOrder);

        printTree(root);
    }

    private static void printTree(Node root) {
        if (root == null) {
            return;
        }

        printTree(root.left);
        System.out.println(root.val);
        printTree(root.right);
    }

    private static int preOrderIndex;
    public static Node constructTree(int[] inOrder, int[] preOrder) {
        preOrderIndex = 0;
        return constructTreeHelper(inOrder, preOrder, 0, inOrder.length - 1);
    }

    public static Node constructTreeHelper(int[] inOrder, int[] preOrder, int inOrderStart, int inOrderEnd) {
        if (preOrderIndex >= inOrder.length || inOrderStart > inOrderEnd) {
            return null;
        }

        int rootVal = preOrder[preOrderIndex++];
        Node root = new Node(rootVal);
        int inOrderIdx = findInOrder(rootVal, inOrder);

        root.left = constructTreeHelper(inOrder, preOrder, inOrderStart, inOrderIdx - 1);
        root.right = constructTreeHelper(inOrder, preOrder, inOrderIdx + 1, inOrderEnd);

        return root;
    }

    private static int findInOrder(int rootVal, int[] inOrder) {
        for (int i = 0; i < inOrder.length; i++) {
            if (rootVal == inOrder[i]) {
                return i;
            }
        }
        return -1;
    }

    static class Node {
        Node left;
        Node right;
        int val;

        Node(int x) {
            this.val = x;
        }
    }
}

import java.util.Stack;

/**
 * Created by kevinto on 2/1/17.
 */
public class InOrderTraversalUsingStack {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.right = new Node(6);
        inOrderStack(root);
    }

    public static void inOrderStack(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        pushLeft(stack, root);

        while (!stack.empty()) {
            Node curr = stack.pop();
            if (curr.right != null) {
                pushLeft(stack, curr.right);
            }
            System.out.print(curr.val + " ");
        }

        System.out.println();
    }

    private static void pushLeft(Stack<Node> stack, Node root) {
        Node curr = root;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
    }

    static class Node {
        Node left;
        Node right;
        int val;

        Node(int val) {
            this.val = val;
        }
    }
}

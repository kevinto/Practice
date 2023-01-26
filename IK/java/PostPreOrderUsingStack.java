import java.util.Stack;

/**
 * Created by kevinto on 2/2/17.
 */
public class PostPreOrderUsingStack {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(8);

        root.right.right = new Node(6);

        postOrderStack(root);
    }

    public static void postOrderStack(Node root) {
        // TODO: Input checking
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node prev = null;

        while(!stack.empty()) {
            Node curr = stack.peek();

            if ((curr.left == null && curr.right == null)
                    || (curr.left == prev || curr.right == prev)) {

                stack.pop();
                prev = curr;
                System.out.print(curr.val + " ");
            } else {
                if (curr.right != null) {
                    stack.push(curr.right);
                }

                if (curr.left != null) {
                    stack.push(curr.left);
                }
            }
        }

        System.out.println();
    }

    public static void preOrderStack(Node root) {
        // TODO: Input checking
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            Node curr = stack.pop();
            System.out.print(curr.val + " ");

            if (curr.right != null) {
                stack.push(curr.right);
            }

            if (curr.left != null) {
                stack.push(curr.left);
            }
        }

        System.out.println();
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

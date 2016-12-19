/**
 * Created by kevinto on 12/19/16.
 */
public class ReusableTests {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(8);

        root.right.right = new Node(6);
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

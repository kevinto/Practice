/**
 * Created by kevinto on 12/21/16.
 */
public class BSTtoLinkedList {
    static void BSTtoLL(Node root) {
        RefObj prev = new RefObj();
        RefObj head = new RefObj();
        getHead(root, prev, head);

        printLL(head);
    }

    static void getHead(Node root, RefObj prev, RefObj head) {
        if (root == null) {
            return;
        }

        getHead(root.left, prev, head);

        if (prev.node == null) {
            head.node = root;
        } else {
            prev.node.right = root;
        }

        root.left = prev.node;
        Node right = root.right;
        root.right = head.node;
        prev.node = root;
        head.node.left = root;

        getHead(right, prev, head);
    }

    static void printLL(RefObj head) {
        if (head.node == null) {
            return;
        }

        Node curr = head.node;
        do {
            System.out.print(curr.val + " ");
            curr = curr.right;
        } while (curr != head.node);
        System.out.println();
    }

    static class RefObj {
        Node node;
    }

    static class Node {
        Node left;
        Node right;
        int val;

        Node (int x) {
            this.val = x;
        }
    }
}

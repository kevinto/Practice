import java.util.*;
import java.util.LinkedList;

public class Solution {
    public static void main(String args[] ) throws Exception {
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);

        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(4);

        System.out.println(isIdentical(root1, root2));
    }

    public static boolean isIdentical(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        } else if (root1.val != root2.val) {
            return false;
        }

        return isIdentical(root1.left, root2.left)
                && isIdentical(root1.right, root2.right);
    }

    static class Node {
        Node left;
        Node right;
        int val;

        Node(int x) {
            val = x;
        }
    }
}


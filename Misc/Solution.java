import java.util.*;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        boolean result = isMatch("aab","c*a*b");
        return;
    }

    static boolean isMatch(String text, String pat) {
        return isMatch(text, text.length() - 1, pat, pat.length() - 1);
    }

    static boolean isMatch(String text, int textPtr, String pat, int patPtr) {
        if (patPtr < 0) {
            return true;
        }else if (textPtr < 0 || patPtr < 0) {
            return false;
        }

        boolean result = false;
        if (pat.charAt(patPtr) != '.' && pat.charAt(patPtr) != '*') {
            if (text.charAt(textPtr) == pat.charAt(patPtr)) {
                result = isMatch(text, textPtr - 1, pat, patPtr - 1);
            } else {
                result = false;
            }
        } else if (pat.charAt(patPtr) == '.') {
            result = isMatch(text, textPtr - 1, pat, patPtr - 1);
        } else if (pat.charAt(patPtr) == '*') {
            // Represents 0 of something. so skip over that something in the pat too
            result = isMatch(text, textPtr, pat, patPtr - 2);

            if (patPtr == 0) {
                return true;
            } else if (pat.charAt(patPtr - 1) == '.') {
                char curr = text.charAt(textPtr);
                for (int i = textPtr - 1; i >= 0; i--) {
                    if (text.charAt(i) != curr) {
                        break;
                    }

                    result |= isMatch(text, i, pat, patPtr - 2);
                }
            } else {
                char curr = pat.charAt(patPtr - 1);
                for (int i = textPtr; i >= 0; i--) {
                    if (text.charAt(i) != curr) {
                        break;
                    }

                    result |= isMatch(text, i - 1, pat, patPtr - 1);
                }
            }
        }

        return result;
    }

    static void printPostOrderIterative(Node root) {
        Node prev = null;
        Stack<Node> stack = new Stack<>();
        pushAllLeft(root, stack);

        while (!stack.empty()) {
            Node top = stack.peek();

            if (top.right == prev || top.right == null) {
                System.out.print(top.val + " ");
                stack.pop();
                prev = top;
            } else { // if (top.right != null)
                pushAllLeft(top.right, stack);
            }
        }
    }

    static void pushAllLeft(Node root, Stack<Node> stack) {
        Node curr = root;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
    }

    static void printPostOrderRecursive(Node root) {
        if (root == null) {
            return;
        }

        printPostOrderRecursive(root.left);
        printPostOrderRecursive(root.right);
        System.out.print(root.val + " ");
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


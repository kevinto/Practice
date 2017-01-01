/**
 * Created by kevinto on 12/31/16.
 */
public class MergeTrees {
    public static void main(String[] args) {
        Node root1 = new Node(2);
        root1.left = new Node(1);
        root1.right = new Node(3);

        Node root2 = new Node(10);
        root2.left = new Node(5);
        root2.right = new Node(15);

        Node mroot = mergeBsts(root1, root2);
        inOrderPrint(mroot);
    }

    public static Node mergeBsts(Node r1, Node r2) {
        Node head1 = flatten(r1);
        Node head2 = flatten(r2);

        Node headMerged = merge(head1, head2);
        //printDll(headMerged);

        return genBst(headMerged);
    }

    private static Node genBstHead;
    private static Node genBst(Node head) {
        genBstHead = head;
        int count = countNodes(head);

        return genBstHelper(count);
    }

    private static Node genBstHelper(int count) {
        if (count <= 0) {
            return null;
        }

        Node left = genBstHelper(count / 2);

        Node root = genBstHead;
        root.left = left;
        genBstHead = genBstHead.right;

        root.right = genBstHelper(count - count / 2 - 1);

        return root;
    }

    private static int countNodes(Node head){
        Node curr = head;
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.right;
        }
        return count;
    }

    private static void inOrderPrint(Node root) {
        if (root == null) {
            return;
        }

        inOrderPrint(root.left);
        System.out.print(root.val + " ");
        inOrderPrint(root.right);
    }

    private static Node merge(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        } else if (head2 == null) {
            return head1;
        }

        Node mergedHead = null;
        Node mergedTail = null;
        Node curr1 = head1;
        Node curr2 = head2;

        while (curr1 != null && curr2 != null) {
            if (curr1.val <= curr2.val) {
                if (mergedHead == null) {
                    mergedHead = curr1;
                    mergedTail = curr1;
                } else {
                    mergedTail.right = curr1;
                    curr1.left = mergedTail;
                    mergedTail = curr1;
                }

                curr1 = curr1.right;
            } else { // curr2.val < curr1.val
                if (mergedHead == null) {
                    mergedHead = curr2;
                    mergedTail = curr2;
                } else {
                    mergedTail.right = curr2;
                    curr2.left = mergedTail;
                    mergedTail = curr2;
                }

                curr2 = curr2.right;
            }
        }

        while (curr1 != null) {
            mergedTail.right = curr1;
            curr1.left = mergedTail;
            mergedTail = curr1;
            curr1 = curr1.right;
        }

        while (curr2 != null) {
            mergedTail.right = curr2;
            curr2.left = mergedTail;
            mergedTail = curr2;
            curr2 = curr2.right;
        }

        return mergedHead;
    }

    private static Node flatHead;
    private static Node flatPrev;
    private static Node flatten(Node root) {
        flatHead = null;
        flatPrev = null;

        flattenHelper(root);

        return flatHead;
    }

    private static void flattenHelper(Node root) {
        if (root == null) {
            return;
        }

        flattenHelper(root.left);

        if (flatHead == null) {
            flatHead = root;
        }

        if (flatPrev != null) {
            root.left = flatPrev;
            flatPrev.right = root;
        }
        flatPrev = root;

        flattenHelper(root.right);
    }

    private static void printDll(Node root) {
        Node curr = root;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.right;
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

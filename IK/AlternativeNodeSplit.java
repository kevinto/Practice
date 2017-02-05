/**
 * Created by kevinto on 12/17/16.
 */
public class AlternativeNodeSplit {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        alternativeSplit(head);
    }

    // Coding exercise problem using curr, next, and head pointers
    public static void alternativeSplitUsingNextPointers(Node head) {
        if (head == null) {
            return;
        }

        Node h1 = head;
        Node h2 = head.next;
        Node c1 = h1;
        Node c2 = h2;

        while (c2 != null) {
            Node n1 = c2.next;
            Node n2 = c2.next == null ? null : c2.next.next;

            c1.next = c2.next;
            c2.next = c2.next == null ? null : c2.next.next;

            c1 = n1;
            c2 = n2;
        }

        printList(h1);
        printList(h2);
    }

    public static void alternativeSplit(Node head) {
        if (head == null) {
            return;
        }

        Node head2 = head.next;
        Node curr1 = head;
        Node curr2 = head2;

        while (curr1 != null && curr1.next != null) {
            curr1.next = curr1.next.next;
            if (curr2.next != null) {
                curr2.next = curr2.next.next;
            }
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        printList(head);
        printList(head2);
    }

    public static void alternativeSplitUsing5Pointers(Node head) {
        if (head == null) {
            return;
        }

        Node head1 = head;
        Node tail1 = head;
        Node head2 = head.next;
        Node tail2 = head.next;
        Node curr = head2;
        int count = 1;

        while (curr != null && head2 != null) {
            if (curr == head2) {
                curr = curr.next;
                continue;
            }

            if (count % 2 == 1) {
                tail1.next = curr;
                tail1 = curr;
            } else {
                tail2.next = curr;
                tail2 = curr;
            }

            curr = curr.next;
            count++;
        }

        tail1.next = null;

        if (tail2 != null) {
            tail2.next = null;
        }

        System.out.println("Printing list 1: ");
        printList(head1);
        System.out.println("Printing list 2: ");
        printList(head2);
    }

    public static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            curr = curr.next;
        }
        System.out.println();
    }

    static class Node {
        Node next;
        int val;

        Node(int newVal) {
            this.val = newVal;
        }
    }
}

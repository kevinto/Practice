/**
 * Created by kevinto on 12/3/16.
 */
public class ZipLinkedList {
    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        System.out.print("Original list: ");
        printLL(head);
        zip(head);
        System.out.print("Zipped list:   ");
        printLL(head);

        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);
        System.out.print("Original list: ");
        printLL(head2);
        zip(head2);
        System.out.print("Zipped list:   ");
        printLL(head2);
    }

    private static void printLL(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static Node zip (Node head) {
        int length = getLength(head);
        if (length < 3) {
            return head;
        }

        int secondListStartIndex = length % 2 == 0 ? (length / 2) : (length / 2) + 1;
        Node secondHalfFirstNode = getNode(head, secondListStartIndex);
        secondHalfFirstNode = reverse(secondHalfFirstNode);

        zip2(head, secondHalfFirstNode);
        return head;
    }

    private static void zip2(Node h1, Node h2) {
        Node c1 = h1;
        Node c2 = h2;

        while (c1 != null || c2 != null) {
            Node next1 = c1 == null ? null : c1.next;
            Node next2 = c2 == null ? null : c2.next;

            if (c1 != null)
                c1.next = c2;
            if (c2 != null)
                c2.next = next1;

            c1 = next1;
            c2 = next2;
        }
    }

    private static void zip(Node firstHalfHead, Node secondHalfHead) {
        Node ptr1 = firstHalfHead;
        Node ptr2 = secondHalfHead;
        Node temp1, temp2;

        while (ptr2 != null && ptr1 != secondHalfHead) {
            temp1 = ptr1.next;
            temp2 = ptr2.next;
            ptr1.next = ptr2;
            ptr2.next = temp1;
            ptr1 = temp1;
            ptr2 = temp2;
        }
        ptr1.next = null;
    }

    private static Node reverse(Node head) {
        Node prev = null;
        Node curr = head;
        Node next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    private static Node getNode(Node head, int secondListStartIndex) {
        Node curr = head;
        int count = secondListStartIndex;
        while (curr != null && count > 1) {
            curr = curr.next;
            count--;
        }
        Node secondHead = curr.next;
        curr.next = null;
        return secondHead;
    }

    private static int getLength(Node head) {
        if (head == null) {
            return 0;
        }

        Node curr = head;
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }

        return count;
    }

    static class Node {
        int val;
        Node next;

        Node(int x) {
            val = x;
        }
    }
}

import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        alternativeSplit(head);
    }

    public static void alternativeSplit(Node head) {
        if (head == null) {
            return;
        }

        Node head1 = head;
        Node tail1 = head;
        Node head2 = null;
        Node tail2 = null;
        Node curr = head;
        int count = 1;

        while (curr != null) {
            if (count % 2 == 1) {
                tail1.next = curr;
                tail1 = curr;
            } else {
                if (head2 == null) {
                    head2 = curr;
                    tail2 = curr;
                } else {
                    tail2.next = curr;
                    tail2 = curr;
                }
            }

            curr = curr.next;
            count++;
        }

        tail1.next = null;
        tail2.next = null;

        printLL(head1);
        printLL(head2);
    }

    public static void printLL(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }
    }

    static class Node {
        Node next;
        int val;

        Node(int newVal) {
            this.val = newVal;
        }
    }
}


import java.util.*;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);

        head.arbit = head.next.next;
        head.next.arbit = head;
        head.next.next.arbit = head.next.next;

        Node head2 = clone(head);
        printLL(head2);
    }

    public static void printLL(Node head) {
        Node curr = head;
        while (curr != null && curr.next != null) {
            System.out.println("curr: " + curr.val + ", next: " + curr.next.val + ", arbit " + curr.arbit.val);
            curr = curr.next;
        }

        System.out.println("curr: " + curr.val + ", next: null, arbit " + curr.arbit.val);
    }

    public static Node clone(Node head) {
        if (head == null) {
            return null;
        }

        create(head);
        setArbit(head);
        Node newList = separate(head);

        return newList;
    }

    public static void create(Node head) {
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            Node newNode = new Node(curr.val);
            curr.next = newNode;
            newNode.next = next;
            curr = next;
        }
    }

    public static void setArbit(Node head) {
        Node curr = head;
        while (curr != null) {
            curr.next.arbit = curr.arbit.next;

            // Curr will always be on an original node.
            // This means that curr.next will always exist
            // because that is a copy.
            curr = curr.next.next;
        }
    }

    public static Node separate(Node head) {
        Node newHead = head.next;
        Node currOld = head;
        Node currNew = newHead;

        while (currOld != null) {
            currOld = currOld.next.next;
            if (currNew.next != null) {
                currNew = currNew.next.next;
            }
            currOld = currOld.next;
            currNew = currNew.next;
        }

        return newHead;
    }

    static class Node {
        Node next;
        Node arbit;
        int val;

        Node(int x) {
            this.val = x;
        }
    }
}


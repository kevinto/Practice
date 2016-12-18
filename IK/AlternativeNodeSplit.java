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

        printList("list1: ", head);
        printList("list2: ", head2);
    }

    public static void printList(String output, Node head) {
        System.out.print(output);
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

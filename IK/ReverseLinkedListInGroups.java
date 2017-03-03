/**
 * Created by kevinto on 1/22/17.
 */
public class ReverseLinkedListInGroups {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);

//        Node newHead = reverse(head, 3);
        Node newHead = reverse(head, 5);
        printLL(newHead);
    }

    private static void printLL(Node newHead) {
        Node curr = newHead;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static Node reverse(Node head, int k) {
        if (head == null) {
            return null;
        }

        int currK = k;
        Node prev = null;
        Node curr = head;

        while (curr != null && currK > 0) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            currK--;
        }

        head.next = reverse(curr, k);
        return prev;
    }

    static class Node {
        Node next;
        int val;

        Node(int val) {
            this.val = val;
        }
    }
}

/**
 * Created by kevinto on 1/2/17.
 */
public class SwapKNodesInLinkedList {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(7);
        head.next.next.next.next.next = new Node(0);

        System.out.println("Before swap: ");
        printLL(head);

        head = swapKNodes(head, 6);

        System.out.println("After swap: ");
        printLL(head);
    }

    // My messy implementation using length counter
    public static Node swapKNodes(Node head, int k) {
        if (head == null) {
            return head;
        }

        int count = getCount(head);
        if (k > count) {
            return head;
        }

        // Get node before the first swap
        int currIdx = 1;
        Node currNode = head;
        Node beforeFirst = null;
        while (currNode != null && currIdx <= k - 1) {
            beforeFirst = currNode;
            currNode = currNode.next;
            currIdx++;
        }

        // Get node before the second swap
        currIdx = 1;
        currNode = head;
        Node beforeSecond = null;
        while (currNode != null && currIdx <= count - k) {
            beforeSecond = currNode;
            currNode = currNode.next;
            currIdx++;
        }

        Node newHead = head;
        if (beforeFirst == null ^ beforeSecond == null) {
            newHead = beforeFirst == null ? beforeSecond.next : beforeFirst.next;
        }

        Node first = beforeFirst == null ? head : beforeFirst.next;
        Node second = beforeSecond == null ? head : beforeSecond.next;
        swapNext(first, second);

        swapNext(beforeFirst, beforeSecond);

        return newHead;
    }

    private static void swapNext(Node n1, Node n2) {
        if (n1 != null && n2 != null) {
            Node temp = n1.next;
            n1.next = n2.next;
            n2.next = temp;
        } else if (n1 == null) {
            n2.next = null;
        } else if (n2 == null) {
            n1.next = null;
        }
    }

    private static int getCount(Node head) {
        int sum = 0;
        Node curr = head;
        while (curr != null) {
            sum++;
            curr = curr.next;
        }
        return sum;
    }

    private static void printLL(Node head) {
        Node curr = head;
        while(curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    private static class Node {
        Node next;
        int val;

        Node(int x) {
            this.val = x;
        }
    }
}

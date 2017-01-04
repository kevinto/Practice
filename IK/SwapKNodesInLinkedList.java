/**
 * Created by kevinto on 1/2/17.
 */
public class SwapKNodesInLinkedList {
    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(4);
        head.next.next.next.next = new LinkedListNode(7);
        head.next.next.next.next.next = new LinkedListNode(0);

        System.out.println("Before swap: ");
        printLL(head);

//        head = swapKNodes(head, 3); // failing for messy implementation
        head = swapKNodes(head, 4); // failing for messy implementation
//        head = swapKNodes(head, 6);
//        head = swapKNodes(head, 1);

        System.out.println("After swap: ");
        printLL(head);
    }

    // My messy implementation using length counter
    public static LinkedListNode swapKNodes(LinkedListNode head, int k) {
        if (head == null) {
            return head;
        }

        int count = getCount(head);
        if (k > count) {
            return head;
        }

        // Get node before the first swap
        int currIdx = 1;
        LinkedListNode currNode = head;
        LinkedListNode beforeFirst = null;
        while (currNode != null && currIdx <= k - 1) {
            beforeFirst = currNode;
            currNode = currNode.next;
            currIdx++;
        }

        // Get node before the second swap
        currIdx = 1;
        currNode = head;
        LinkedListNode beforeSecond = null;
        while (currNode != null && currIdx <= count - k) {
            beforeSecond = currNode;
            currNode = currNode.next;
            currIdx++;
        }

        LinkedListNode newHead = head;
        if (beforeFirst == null ^ beforeSecond == null) {
            newHead = beforeFirst == null ? beforeSecond.next : beforeFirst.next;
        }

        // Swap the nodes after the nodes we want to swap.
        LinkedListNode first = beforeFirst == null ? head : beforeFirst.next;
        LinkedListNode second = beforeSecond == null ? head : beforeSecond.next;
        swapNext(first, second);

        // Swap the actual nodes we want to swap.
        swapNext(beforeFirst, beforeSecond);

        // Take care of the case where the before node is null.
        // If this is true, then we need to set the last node manually.
        if (beforeFirst == null) {
            beforeSecond.next = first;
        } else if (beforeSecond == null) {
            beforeFirst.next = second;
        }

        return newHead;
    }

    private static void swapNext(LinkedListNode n1, LinkedListNode n2) {
        if (n1 != null && n2 != null) {
            LinkedListNode temp = n1.next;
            n1.next = n2.next;
            n2.next = temp;
        } else if (n1 == null) {
            n2.next = null;
        } else if (n2 == null) {
            n1.next = null;
        }
    }

    private static int getCount(LinkedListNode head) {
        int sum = 0;
        LinkedListNode curr = head;
        while (curr != null) {
            sum++;
            curr = curr.next;
        }
        return sum;
    }

    private static void printLL(LinkedListNode head) {
        LinkedListNode curr = head;
        while(curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    private static class LinkedListNode {
        LinkedListNode next;
        int val;

        LinkedListNode(int x) {
            this.val = x;
        }
    }
}

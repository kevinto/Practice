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
//        head = swapKNodes(head, 4); // failing for messy implementation
        head = swapKNodes(head, 6);
//        head = swapKNodes(head, 1);

        System.out.println("After swap: ");
        printLL(head);
    }

    // -------- My clean implementation using no length counter ----------------
    static LinkedListNode swapNodes(LinkedListNode head, int k) {
        if (head == null) {
            return null;
        }

        if (count(head) < k) {
            return head;
        }

        LinkedListNode beforeFront = null;
        LinkedListNode front;
        LinkedListNode beforeBack = null;
        LinkedListNode back = head;
        LinkedListNode temp = head;
        int ct = k;

        while (ct > 1 && temp != null) {
            beforeFront = temp;
            temp = temp.next;
            ct--;
        }
        front = temp;

        // Uses temp to take advantage of the sliding window which when temp reaches the end,
        // the beginning of the window is k distance from the end
        while (temp != null && temp.next != null) {
            beforeBack = back;
            back = back.next;
            temp = temp.next;
        }

        // We are deciding to swap the before nodes first before swapping the next of the front/back nodes.
        return swapNodes(head, beforeFront, front, beforeBack, back);
    }

    private static LinkedListNode swapNodes(LinkedListNode head, LinkedListNode beforeFront, LinkedListNode front, LinkedListNode beforeBack, LinkedListNode back) {
        if (beforeFront != null && beforeBack != null) {
            beforeFront.next = back;
            beforeBack.next = front;
        } else if (beforeFront == null) {
            head = back;
            beforeBack.next = front;
//        } else if (beforeBack == null) {
        } else {
            head = front;
            beforeFront.next = back;
        }

        LinkedListNode temp = front.next;
        front.next = back.next;
        back.next = temp;

        return head;
    }

    private static int count(LinkedListNode head) {
        int sum = 0;
        LinkedListNode curr = head;
        while (curr != null) {
            sum++;
            curr = curr.next;
        }

        return sum;
    }

    // -------- My messy implementation using length counter ----------------
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

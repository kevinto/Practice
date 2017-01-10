/**
 * Created by kevinto on 12/3/16.
 */
public class ZipLinkedList {
    public static void main(String[] args) {

        LinkedListNode head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(4);
        head.next.next.next.next = new LinkedListNode(5);
        head.next.next.next.next.next = new LinkedListNode(6);
        System.out.print("Original list: ");
        printLL(head);
        zip(head);
        System.out.print("Zipped list:   ");
        printLL(head);

        LinkedListNode head2 = new LinkedListNode(1);
        head2.next = new LinkedListNode(2);
        head2.next.next = new LinkedListNode(3);
        head2.next.next.next = new LinkedListNode(4);
        head2.next.next.next.next = new LinkedListNode(5);
        System.out.print("Original list: ");
        printLL(head2);
        zip(head2);
        System.out.print("Zipped list:   ");
        printLL(head2);
    }

    private static void printLL(LinkedListNode head) {
        LinkedListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static LinkedListNode zip (LinkedListNode head) {
        int length = getLength(head);
        if (length < 3) {
            return head;
        }

        int secondListStartIndex = length % 2 == 0 ? (length / 2) : (length / 2) + 1;
        LinkedListNode secondHalfFirstNode = getNode(head, secondListStartIndex);
        secondHalfFirstNode = reverse(secondHalfFirstNode);

        zip(head, secondHalfFirstNode);
        return head;
    }

    private static void zip(LinkedListNode firstHalfHead, LinkedListNode secondHalfHead) {
        LinkedListNode ptr1 = firstHalfHead;
        LinkedListNode ptr2 = secondHalfHead;
        LinkedListNode temp1, temp2;

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

    private static LinkedListNode reverse(LinkedListNode head) {
        LinkedListNode prev = null;
        LinkedListNode curr = head;
        LinkedListNode next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    private static LinkedListNode getNode(LinkedListNode head, int secondListStartIndex) {
        LinkedListNode curr = head;
        int count = 0;
        while (curr != null) {
            if (count == secondListStartIndex) {
                return curr;
            }
            count++;
            curr = curr.next;
        }

        return null;
    }

    private static int getLength(LinkedListNode head) {
        if (head == null) {
            return 0;
        }

        LinkedListNode curr = head;
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }

        return count;
    }

    static class LinkedListNode {
        int val;
        LinkedListNode next;

        LinkedListNode (int x) {
            val = x;
        }
    }
}

/**
 * Created by kevinto on 12/18/16.
 */
public class MiddleElementOfLinkedList {
    static int findMiddleNode(LinkedListNode head) {
        if (head == null) {
            return -1;
        }

        LinkedListNode first = head;
        LinkedListNode second = head;
        while(second != null && second.next != null) {
            first = first.next;
            second = second.next.next;
        }

        return first.val;
    }

    private static class LinkedListNode {
        LinkedListNode next;
        int val;

        LinkedListNode(int x) {
            this.val = x;
        }
    }
}

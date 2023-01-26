/**
 * Created by kevinto on 12/18/16.
 */
public class MiddleElementOfLinkedList {
    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(4);
        head.next.next.next.next = new LinkedListNode(5);
        head.next.next.next.next.next = new LinkedListNode(6);
        System.out.println(findMiddleNode(head));
    }

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

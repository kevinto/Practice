/**
 * Created by Kevin on 7/24/16.
 */
public class InsSortLinkedList {
    public ListNode insertionSortList(ListNode A) {
        if (A == null) return null;

        ListNode head = A;
        ListNode node;
        ListNode prev;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        if (A.next == null) {
            return A;
        }

        node = A;
        while (node.next != null) {
            node = node.next;
        }

        prev = getPrev(dummyHead, node);

        while(prev != null) {
            ListNode temp = prev;

            while (node.next != null && node.val > node.next.val) {
                exchange(prev, node, node.next);
                prev = prev.next;
            }

            node = temp;
            prev = getPrev(dummyHead, node);
        }

        return dummyHead.next;
    }

    public ListNode exchange(ListNode prev, ListNode node, ListNode next) {
        if (prev != null) {
            prev.next = next;
        }

        node.next = next.next;
        next.next = node;

        return node;
    }

    public ListNode getPrev(ListNode head, ListNode node) {
        if (head == node) return null;

        while (head.next != node) {
            head = head.next;
        }
        return head;
    }

    class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            this.val = x;
            next = null;
        }
    }
}

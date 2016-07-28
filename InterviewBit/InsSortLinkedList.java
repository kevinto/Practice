import java.util.List;

/**
 * Created by Kevin on 7/24/16.
 */
public class InsSortLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(4);

        ListNode newHead = insertionSortList(head);
        printList(newHead);
        return;
    }

    public static ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = head;
        ListNode next = null;

        // Go through the original list and remove elements starting from the
        // top of the list and add it into the sorted list.
        while (curr != null) {
            next = curr.next;
            addToSorted(dummyHead, curr);
            curr = next;
        }

        // The dummy allows us to easily track the changes in the start
        // of the list due to the changes in ordering.
        return dummyHead.next;
    }

    public static void addToSorted(ListNode dummy, ListNode newNode) {
        ListNode prev = dummy;
        ListNode curr = dummy.next;

        // Go through the sorted list and insert the newNode in the
        // correct ascending order.
        while (curr != null) {
            if (newNode.val <= curr.val) {
                prev.next = newNode;
                newNode.next = curr;
                return;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }

        // Only reach here if newNode is the max item. Add to end of
        // the list.
        prev.next = newNode;
        newNode.next = null;
    }

    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            this.val = x;
            next = null;
        }
    }
}

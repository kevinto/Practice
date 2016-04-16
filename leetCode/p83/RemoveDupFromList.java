package p83;

/**
 * Created by kevin on 4/15/2016.
 */
public class RemoveDupFromList {
    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(1);
        root.next.next = new ListNode(2);
        root.next.next.next = new ListNode(3);
        root.next.next.next.next = new ListNode(3);
        deleteDuplicates(root);
        return;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode prev = null;
        ListNode root = head;
        while (head != null) {
            if (prev != null && prev.val == head.val) {
                prev.next = head.next;
                head = head.next;
                continue;
            }
            prev = head;
            head = head.next;
        }

        return root;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

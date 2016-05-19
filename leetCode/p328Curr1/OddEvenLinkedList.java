package p328Curr1;

/**
 * Created by Kevin on 5/19/16.
 *
 * Link: https://leetcode.com/problems/odd-even-linked-list/
 *
 *  Given 1->2->3->4->5->NULL,
 *  return 1->3->5->2->4->NULL.
 *
 *  Given 1->2->3->4->5->NULL,
 *  return 1->3->2->4->NULL.
 */
public class OddEvenLinkedList {
    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        ListNode testRoot = oddEvenList(root);
        printList(testRoot);
    }

    public static ListNode oddEvenList(ListNode head) {
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }

    public static void printList(ListNode root) {
        ListNode currNode = root;
        while (currNode != null) {
            System.out.println(currNode.val);
            currNode = currNode.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

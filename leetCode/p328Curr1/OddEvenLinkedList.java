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


    public ListNode oddEvenList(ListNode head) {
        // set param head to odd list
        // create new even head
        // create new tail for even head

        // set currOddNode to first list item
        // set currEvenNode to second list item
        // while currEvenNode != null
            // if even head is null
                // set even head to currEvenNode
                // set even tail to head
            // else
                // set even tail.next = currEvenNode

            // currEvenNode = currEvenNode.next.next;

            // currOddNode.next = currOddNode.next.next;
            // currOddNode = currOddNode.next;

       //    o  e
//           1->2->3->4->5->NULL

        //         o
//           1->2->3->4->5->NULL
        return null;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

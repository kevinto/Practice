/*

Link: https://leetcode.com/problems/reverse-linked-list/
Problem: Reverse a singly linked list.

1. Do we want to reverse in place? YES
2. 
*/

package p206;

public class ReverseLL {
    public static void main(String[] args) {
        LinkNode l1  = new LinkNode(1);
        LinkNode l2  = new LinkNode(2);
        LinkNode l3  = new LinkNode(3);
        l1.next = l2;
        l2.next = l3;
        l3.next = null;
        
        System.out.println("Running iterative tests: ");
        LinkNode reversedList1 = reverseIterative(l1);
        printList(reversedList1);
        
        LinkNode k1  = new LinkNode(1);
        LinkNode k2  = new LinkNode(2);
        LinkNode k3  = new LinkNode(3);
        k1.next = k2;
        k2.next = k3;
        k3.next = null;
        
        System.out.println("Running recursive tests: ");
        LinkNode reversedList2 = reverseRecursive1(k1);
        printList(reversedList2);
    }
    
    public static LinkNode reverseIterative(LinkNode head) {
        LinkNode previous = null;
        while (head != null) {
            LinkNode next = head.next;
            head.next = previous;
            previous = head;
            head = next;
        }
        return previous;
    }
    
    // 1 -> 2 -> 3
    public static LinkNode reverseRecursive1(LinkNode head) {
        // Base case
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        
        // Bug: This only returns the last element of the linked list.
        // LinkNode temp = reverseRecursive1(head.next);
        // temp.next = head;
        // head.next = null;
        
        // This is correct
        // Explanation: http://stackoverflow.com/questions/354875/reversing-a-linked-list-in-java-recursively
        LinkNode secondElement = head.next; // save the next element
        head.next = null; // unlink the current node.
        LinkNode reverseRest = reverseRecursive1(secondElement); // go to the next node
        secondElement.next = head; // set the next node's next to the current node
        
        return reverseRest;
    }

    // Alternative recursive implementation that i came up with. requires an extra param
    public static LinkNode reverseRecursive(LinkNode head, LinkNode prev) {
        if (head == null) {
            return null;
        } else if (head.next == null) {
            head.next = prev;
            return head;
        }

        LinkNode newHead = reverseRecursive(head.next, head);
        head.next = prev;
        return newHead;
    }
    
    public static void printList(LinkNode head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }
}

class LinkNode {
    int value;
    LinkNode next;
    public LinkNode(int x) { value = x; }
}
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
        LinkNode reversedList2 = reverseRecursive(k1);
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
    public static LinkNode reverseRecursive(LinkNode head) {
        // Base case
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        
        // Bug: This only returns the last element of the linked list.
        // LinkNode temp = reverseRecursive(head.next);
        // temp.next = head;
        // head.next = null;
        
        // This is correct
        // Explanation: http://stackoverflow.com/questions/354875/reversing-a-linked-list-in-java-recursively
        LinkNode secondElement = head.next;
        head.next = null;
        LinkNode reverseRest = reverseRecursive(secondElement);
        secondElement.next = head;
        
        return reverseRest;
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
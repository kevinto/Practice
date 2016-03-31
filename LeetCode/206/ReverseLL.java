/*

Link: https://leetcode.com/problems/reverse-linked-list/
Problem: Reverse a singly linked list.

1. Do we want to reverse in place? YES
2. 
*/
public class ReverseLL {
    public static void main(String[] args) {
        LinkNode l1  = new LinkNode(1);
        LinkNode l2  = new LinkNode(2);
        LinkNode l3  = new LinkNode(3);
        l1.next = l2;
        l2.next = l3;
        l3.next = null;
        
        LinkNode reversedList = reverse(l1);
        // System.out.println(l3.next.next.value);
        printList(reversedList);
    }
    
    // 1 -> 2 -> 3
    public static LinkNode reverse(LinkNode head) {
        LinkNode previous = null;
        while (head != null) {
            LinkNode next = head.next;
            head.next = previous;
            previous = head;
            head = next;
        }
        return previous;
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
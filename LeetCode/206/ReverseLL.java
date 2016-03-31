/*

Link: https://leetcode.com/problems/reverse-linked-list/
Problem: Reverse a singly linked list.

1. Do we want to reverse in place?
2. 
*/
import java.util.LinkedList;
public class ReverseLL {
    public static void main(String[] args) {
        // LinkedList<String> ll = new LinkedList<String>();
        // ll.add("A");
        // ll.add("B");
        // ll.add("C");
        
        // Test whether an object copy is made or the reference is copied
        // LinkNode n  = new LinkNode();
        // n.here = true;
        // LinkNode m = n;
        // System.out.println(n.here);
        // m.here = false;
        // System.out.println(n.here);
        
        LinkNode n  = new LinkNode(1);
        System.out.println(n.value);
    }
}

class LinkNode {
    int value;
    LinkNode next;
    public LinkNode(int x) { value = x; }
}
package ch2LinkedLists;

/*
 * Problem 2.2 Statement:
 * Return Kth to Last: Implement an algorithm to find the kth to last 
 * element of a singly linked list.
 */

public class ReturnKtoLast {
    public static void main(String[] args) {
        Node22 head = new Node22(2);
        Node22 head2 = new Node22(3);
        Node22 head3 = new Node22(4);
        Node22 head4 = new Node22(5);
        head.next = head2;
        head2.next = head3;
        head3.next = head4;

        System.out.println(findKthToLast(head, 1).val);
        System.out.println(findKthToLast(head, 2).val);
    }

    public static Node22 findKthToLast(Node22 head, int k) {
        Index idx = new Index(0);
        return findKthToLast(head, k, idx);
    }


    public static Node22 findKthToLast(Node22 head, int k, Index idx) {
        if (head == null) {
            return null;
        }

        Node22 node22 = findKthToLast(head.next, k, idx);

        // You have to also imagine that you have the correct index back!
        idx.index++;
        if (idx.index == k) {
            return head;
        }
        return node22;
    }
}

class Index {
    int index;
    Index (int x) {
        index = x;
    }
}

class Node22 {
    int val;
    Node22 next;

    Node22(int x) {
        val = x;
        next = null;
    }
}

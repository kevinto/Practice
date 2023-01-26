/**
 * Created by kevinto on 12/12/16.
 */
public class SegmentLinkedListBasedOnTarget {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(10);
        head.next.prev = head;

        head.next.next = new Node(2);
        head.next.next.prev = head.next;

        head.next.next.next = new Node(12);
        head.next.next.next.prev = head.next.next;

        head.next.next.next.next = new Node(3);
        head.next.next.next.next.prev = head.next.next.next;


        head.next.next.next.next.next = new Node(11);
        head.next.next.next.next.next.prev = head.next.next.next.next;
        head = segment(head, 9);
        return;
    }

    public static Node segment(Node head, int target) {
        int nodesProcessed = 0;
        int linkedListLength = getLength(head);
        Node headPtr = new Node(true);
        Node tailPtr = new Node(true);
        headPtr.next = head;
        tailPtr.prev = getLastNode(head);

        Node front = head;
        head.prev = headPtr;
        tailPtr.prev.next = tailPtr;

        while (nodesProcessed < linkedListLength && front != null) {
            if (front.val < target) {
                front.prev.next = front.next;
                front.next.prev = front.prev;
                Node next = front.next;
                front.next = headPtr.next;
                headPtr.next.prev = front;
                headPtr.next = front;
                front = next;
            } else if (front.val > target) {
                front.prev.next = front.next;
                front.next.prev = front.prev;
                Node next = front.next;
                front.next = tailPtr.next;
                front.prev = tailPtr.prev;
                tailPtr.prev.next = front;
                tailPtr.prev = front;
                front = next;
            } else {
                front = front.next;
            }
            nodesProcessed++;
        }

        return headPtr.next;
    }

    private static int getLength(Node head) {
        Node curr = head;
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    private static Node getLastNode(Node head) {
        Node curr = head;
        if (curr == null) {
            return null;
        }

        while (curr.next != null) {
            curr = curr.next;
        }

        return curr;
    }

    static class Node {
        Node next;
        Node prev;
        int val;
        boolean isSentinal;

        Node(int x) {
            val = x;
            this.isSentinal = false;
        }

        Node(boolean isSentinal) {
            this.isSentinal = isSentinal;
        }
    }
}

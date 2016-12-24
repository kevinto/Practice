/**
 * Created by kevinto on 12/24/16.
 */
public class IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);

        Node head2 = new Node(5);
        head2.next = head1.next.next;

        Node intersection = findPathUsingLength(head1, head2);
        System.out.println("using len: " + intersection.val);

        intersection = findPathByResetting(head1, head2);
        System.out.println("using reset long: " + intersection.val);

        intersection = findPathByResetShort(head1, head2);
        System.out.println("using reset short: " + intersection.val);
    }


    public static Node findPathByResetShort(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        Node p1 = head1;
        Node p2 = head2;

        while (p1 != p2) {
            p1 = p1 == null ? head2 : p1.next;
            p2 = p2 == null ? head1 : p2.next;
        }

        return p1;
    }

    public static Node findPathByResetting(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        boolean h1Reset = false;
        boolean h2Reset = false;
        Node p1 = head1;
        Node p2 = head2;
        while (p1 != null && p2 != null && (!h1Reset || !h2Reset)) {
            if (p1 == p2) {
                return p1;
            }

            if (p1.next == null) {
                p1 = head2;
                h1Reset = true;
                continue;
            } else if (p2.next == null) {
                p2 = head1;
                h2Reset = true;
                continue;
            }

            p1 = p1.next;
            p2 = p2.next;
        }

        while (p1 != null && p2 != null && (p1 != p2)) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

    public static Node findPathUsingLength(Node head1, Node head2){
        if (head1 == null || head2 == null) {
            return null;
        }

        int h1Len = getLen(head1);
        int h2Len = getLen(head2);
        Node h1Ptr = head1;
        Node h2Ptr = head2;

        if (h1Len > h2Len) {
            int movesLeft = h1Len - h2Len;
            while (h1Ptr != null && movesLeft > 0) {
                h1Ptr = h1Ptr.next;
                movesLeft--;
            }
        } else if (h2Len > h1Len) {
            int movesLeft = h2Len - h1Len;
            while (h2Ptr != null && movesLeft > 0) {
                h2Ptr = h2Ptr.next;
                movesLeft--;
            }
        }

        while (h1Ptr != null && h2Ptr != null && h1Ptr != h2Ptr) {
            h1Ptr = h1Ptr.next;
            h2Ptr = h2Ptr.next;
        }

        if (h1Ptr == h2Ptr) {
            return h1Ptr;
        } else {
            return null;
        }
    }

    public static int getLen(Node head) {
        Node curr = head;
        int len = 0;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        return len;
    }


    private static class Node {
        Node next;
        int val;

        Node(int newVal) {
            this.val = newVal;
        }
    }
}

/**
 * Created by kevinto on 2/7/17.
 *
 * Problem asked during LincedIn
 */
public class LinkedListWithCycle {
    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = head1.next;

        Node head2 = new Node(5);
        head2.next = head1.next.next;

        Node intersection = findInter(head1, head2);
        System.out.println("using len: " + intersection.val);
    }

    private static Node oldEnd;
    private static Node nodeBeforeEnd;

    public static Node findInter(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        oldEnd = null;
        nodeBeforeEnd = head1;
        resetCycle(head1);

        Node slow = head2;
        Node fast = head2;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                break;
            }
        }

        // Remember we will always have a cycle
        int cycleLen = 1;
        slow = slow.next;
        while (slow != fast) {
            slow = slow.next;
            cycleLen++;
        }

        // Move first pointer ahead by length of the cycle
        Node curr1 = head2;
        Node curr2 = head2;
        while (cycleLen > 0) {
            curr1 = curr1.next;
            cycleLen--;
        }

        // Find the intersection
        while (curr1 != curr2) {
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        Node inter = curr1;

        restoreOldCycle();
        return inter;
    }

    private static void restoreOldCycle() {
        nodeBeforeEnd.next = oldEnd;
    }

    private static void resetCycle(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }

        // There is no cycle
        if (fast == null || fast.next == null) {
            oldEnd = null;

            Node curr = head;
            while (curr != null && curr.next != null) {
                curr = curr.next;
            }
            nodeBeforeEnd = curr;
            nodeBeforeEnd.next = head;
            return;
        }

        int cycleLen = 1;
        slow = slow.next;
        while (slow != fast) {
            slow = slow.next;
            cycleLen++;
        }

        Node curr1 = head;
        Node curr2 = head;
        while (cycleLen > 0) {
            curr1 = curr1.next;
            cycleLen--;
        }

        while (curr1 != curr2) {
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        oldEnd = curr1;
        Node curr = head;
        while (curr.next != oldEnd) {
            curr = curr.next;
        }
        nodeBeforeEnd = curr;
    }

    private static class Node {
        Node next;
        int val;

        Node(int newVal) {
            this.val = newVal;
        }
    }
}

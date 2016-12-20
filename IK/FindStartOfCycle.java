/**
 * Created by kevinto on 12/20/16.
 */
public class FindStartOfCycle {
    public static void main(String[] args) {
        // 1 -> 2 -> 3 -> 4 -> 5 -> loops back to 3
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = head.next.next;
        System.out.println(findStartCycle(head));
    }

    public static int findStartCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            if (fast == slow) {
                System.out.println("Cycle exists.");
                break;
            }
            slow = slow.next;
        }

        if (fast == null || fast.next == null) {
            System.out.println("Cycle doesn't exist.");
            return -1;
        }

        // Freeze fast and check length of cycle
        int len = 1;
        slow = slow.next;
        while (fast != slow) {
            slow = slow.next;
            len++;
        }

        // Go back to the beginning of the list and
        // have two pointers going at regular speed.
        Node ptrFirst = head;
        Node ptrSecond = head;

        // Move the first pointer up the len of the cycle
        int moves = 0;
        while (moves != len) {
            ptrFirst = ptrFirst.next;
            moves++;
        }

        // When the two pointers meet, we have the start of the cycle
        while (ptrFirst != ptrSecond) {
            ptrFirst = ptrFirst.next;
            ptrSecond = ptrSecond.next;
        }

        return ptrFirst.val;
    }

    private static class Node {
        Node next;
        int val;

        Node(int x) {
            this.val = x;
        }
    }
}

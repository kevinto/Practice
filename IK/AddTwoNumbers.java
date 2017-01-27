/**
 * Created by kevinto on 1/26/17.
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        LinkedListNode head1 = new LinkedListNode(9);
        head1.next = new LinkedListNode(9);
        head1.next.next = new LinkedListNode(9);

        LinkedListNode head2 = new LinkedListNode(9);
        //head2.next = new LinkedListNode(2);

        LinkedListNode res = addNumbers(head1, head2);
        printLL(res);
    }

    static LinkedListNode addNumbers(LinkedListNode head1, LinkedListNode head2) {
        LinkedListNode curr1 = head1;
        LinkedListNode curr2 = head2;
        LinkedListNode newHead = null;
        LinkedListNode tail = null;
        int carry = 0;

        while (curr1 != null && curr2 != null) {
            int newSum = curr1.val + curr2.val + carry;
            if (newHead == null) {
                newHead = new LinkedListNode(newSum);
                tail = newHead;
            } else {
                tail.next = new LinkedListNode(newSum);
                tail = tail.next;
            }
            carry = getCarry(tail);

            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        while (curr1 != null) {
            int newSum = curr1.val + carry;
            if (newHead == null) {
                newHead = new LinkedListNode(newSum);
                tail = newHead;
            } else {
                tail.next = new LinkedListNode(newSum);
                tail = tail.next;
            }

            carry = getCarry(tail);
            curr1 = curr1.next;
        }

        while (curr2 != null) {
            int newSum = curr2.val + carry;
            if (newHead == null) {
                newHead = new LinkedListNode(newSum);
                tail = newHead;
            } else {
                tail.next = new LinkedListNode(newSum);
                tail = tail.next;
            }

            carry = getCarry(tail);
            curr2 = curr2.next;
        }

        if (carry == 1) {
            tail.next = new LinkedListNode(1);
            tail = tail.next;
        }

        return newHead;
    }

    private static int getCarry(LinkedListNode curr) {
        int carry = 0;

        if (curr.val >= 10) {
            curr.val = curr.val - 10;
            carry = 1;
        } else {
            carry = 0;
        }

        return carry;
    }

    public static void printLL(LinkedListNode head) {
        LinkedListNode curr = head;
        while(curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    static class LinkedListNode {
        int val;
        LinkedListNode next;

        LinkedListNode(int val) {
            this.val = val;
        }
    }
}

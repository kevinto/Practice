/**
 * Created by kevinto on 12/4/16.
 */
public class CloneSpecialList {
    public static void main(String[] args) {
        LinkedListArbitNode head = new LinkedListArbitNode(1);
        LinkedListArbitNode node2 = new LinkedListArbitNode(2);
        LinkedListArbitNode node3 = new LinkedListArbitNode(3);
        LinkedListArbitNode node4 = new LinkedListArbitNode(4);
        LinkedListArbitNode node5 = new LinkedListArbitNode(5);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        head.arbit = node3;
        node2.arbit = head;
        node3.arbit = node5;
        node4.arbit = node3;
        node5.arbit = node2;

        LinkedListArbitNode copyHead = copyList(head);
    }

    public static LinkedListArbitNode copyList(LinkedListArbitNode oldHead) {
        createLinkedCopies(oldHead);
        setCopyArbits(oldHead);

        LinkedListArbitNode newHead = getCopyHead(oldHead);
        linkCopiesTogether(oldHead);

        return newHead;
    }

    private static void linkCopiesTogether(LinkedListArbitNode oldHead) {
        LinkedListArbitNode curr = oldHead;
        while (curr != null && curr.next != null) {
            LinkedListArbitNode currCopy = curr.next;
            curr.next = currCopy.next;
            currCopy.next = curr.next == null ? null : curr.next.next;
            curr = curr.next;
        }
    }

    private static LinkedListArbitNode getCopyHead(LinkedListArbitNode oldHead) {
        return oldHead.next;
    }

    private static void setCopyArbits(LinkedListArbitNode oldHead) {
        LinkedListArbitNode curr = oldHead;
        while(curr != null) {
            curr.next.arbit = curr.arbit.next;
            curr = curr.next.next;
        }
    }

    private static void createLinkedCopies(LinkedListArbitNode oldHead) {
        LinkedListArbitNode curr = oldHead;
        while (curr != null) {
            LinkedListArbitNode temp = curr.next;
            curr.next = new LinkedListArbitNode(curr.val);
            curr.next.next = temp;
            curr = temp;
        }
    }

    static class LinkedListArbitNode {
        LinkedListArbitNode next;
        LinkedListArbitNode arbit;
        int val;

        LinkedListArbitNode(int x) {
            val = x;
        }
    }
}

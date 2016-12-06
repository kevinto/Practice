/**
 * Created by kevinto on 12/5/16.
 */
public class MergeSortLinkedList {
    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(4);
        head.next = new LinkedListNode(3);
        head.next.next = new LinkedListNode(5);
        head.next.next.next = new LinkedListNode(1);

        LinkedListNode newHead = mergeSortList(head);
        linkedListPrint(newHead);
    }

    static void linkedListPrint(LinkedListNode head) {
        LinkedListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            curr = curr.next;
        }
    }

    static LinkedListNode mergeSortList(LinkedListNode head) {
        int listSize = getLength(head);
        return mergeSortList(head, listSize);
    }

    static int getLength(LinkedListNode head) {
        int count = 0;
        LinkedListNode curr = head;
        while(curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    static LinkedListNode mergeSortList(LinkedListNode head, int size) {
        if (size == 0 || size == 1) {
            return head;
        }

        LinkedListNode prev = head;
        LinkedListNode tail = head;
        int mid = size / 2;

        for (int i = 0; i < mid; i++) {
            prev = tail;
            tail = tail.next;
        }
        prev.next = null;

        LinkedListNode sortedFirstHalf = mergeSortList(head, mid);
        LinkedListNode sortedSecondHalf = mergeSortList(tail, size - mid);
        LinkedListNode sortedList = merge(sortedFirstHalf, sortedSecondHalf);
        return sortedList;
    }

    static LinkedListNode merge(LinkedListNode firstList, LinkedListNode secondList) {
        LinkedListNode firstListPointer = firstList;
        LinkedListNode secondListPointer = secondList;
        LinkedListNode resultHead = null;
        LinkedListNode resultTail = null;

        while (firstListPointer != null && secondListPointer != null) {
            if (firstListPointer.val < secondListPointer.val) {
                if (resultHead == null) {
                    resultHead = firstListPointer;
                    resultTail = resultHead;
                } else {
                    resultTail.next = firstListPointer;
                    resultTail = resultTail.next;
                }
                firstListPointer = firstListPointer.next;
            } else {
                if (resultHead == null) {
                    resultHead = secondListPointer;
                    resultTail = resultHead;
                } else {
                    resultTail.next = secondListPointer;
                    resultTail = resultTail.next;
                }
                secondListPointer = secondListPointer.next;
            }
        }

        while (firstListPointer != null) {
            resultTail.next = firstListPointer;
            resultTail = resultTail.next;
            firstListPointer = firstListPointer.next;
        }

        while (secondListPointer != null) {
            resultTail.next = secondListPointer;
            resultTail = resultTail.next;
            secondListPointer = secondListPointer.next;
        }

        return resultHead;
    }

    static class LinkedListNode {
        LinkedListNode next;
        int val;

        LinkedListNode(int x) {
            val = x;
        }
    }
}

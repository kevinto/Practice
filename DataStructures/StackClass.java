///**
// * Created by Kevin on 4/6/16.
// *
// * Stack is a FIFO data structure.
// * Main methods:
// *      push() - adds a new item to the top of the stack.
// *      pop() - removes the top item and returns it to the caller
// */
//public class StackClass {
//    public static void main(String[] args) {
//        Stackx s = new Stackx();
//        s.push(1);
//        s.push(2);
//        LinkedListNode poppedNode = s.pop();
//        s.printStack();
//        System.out.println("popped node: " + poppedNode.value);
//    }
//}
//
//class Stackx {
//    private int size = 0;
//    private LinkedListNode head;
//
//    int getSize() {
//        return size;
//    }
//
//    void push(int value) {
//        if (head == null) {
//            head = new LinkedListNode(value);
//            size++;
//        }
////        else {
//            LinkedListNode newHead = new LinkedListNode(value);
//            newHead.next = head;
//            head = newHead;
//            size++;
//        }
//    }
//
//    LinkedListNode pop() {
//        if (head == null) {
//            return null;
//        }
//
//        LinkedListNode retNode = head;
//        head = head.next;
//
//        return retNode;
//    }
//
//    // For debugging
//    void printStack() {
//        LinkedListNode currPtr = head;
//        while (currPtr != null) {
//            System.out.println(currPtr.value);
//            currPtr = currPtr.next;
//        }
//    }
//}

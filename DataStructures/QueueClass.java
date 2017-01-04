///**
// * Created by Kevin on 4/7/16.
// *
// * 1. enqueue(x, q) - pushes to the end of the queue
// * 2. dequeue(q) method - gets and removes from the top of the queue
// *
// * Questions to ask before using array vs linked list:
// * 1. Do we know the size of the queue beforehand?
// * 2. Is space a concern?
// *
// */
//public class QueueClass {
//    public static void main(String[] args) {
//        Queuex q = new Queuex();
//
//        System.out.println("Creating new queue: ");
//        q.enqueue(1);
//        q.enqueue(2);
//        q.enqueue(3);
//
//        q.printQueue();
//
//        System.out.println("Get first item from queue: " + q.dequeue().value);
//        System.out.println("Rest of the queue: ");
//        q.printQueue();
//    }
//}
//
//class Queuex {
//    private LinkedListNode head = null;
//    private LinkedListNode tail = null;
//
//    void enqueue(int x) {
//        LinkedListNode newNode = new LinkedListNode(x);
//
//        if (head == null) {
//            head = newNode;
//            tail = newNode;
//        }
//        else {
//            tail.next = newNode;
//            tail = newNode;
//        }
//    }
//
//    LinkedListNode dequeue() {
//        if (head == null || tail == null) {
//            return null;
//        }
//        else {
//            LinkedListNode top = head;
//            head = head.next;
//            return top;
//        }
//    }
//
//    void printQueue() {
//        LinkedListNode temp = head;
//        while (temp != null) {
//            System.out.println(temp.value);
//            temp = temp.next;
//        }
//    }
//}

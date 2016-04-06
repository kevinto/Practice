/*
Linked list operations:
 - Access (given an index)
 - Delete
 - Search
 - Add
 */

public class LinkedList {
    public static void main(String[] args) {
        System.out.println("New list created: ");
        LinkedListDs ll = createTestLinkedList();
        ll.printList();
        System.out.println("size: " + ll.size);

        runDeleteTests();
    }

    public static void runDeleteTests() {
        LinkedListDs ll = createTestLinkedList();

        System.out.println("Deleting head: ");
        ll.delete(1);
        ll.printList();
        System.out.println("size: " + ll.size);

        System.out.println("Deleting tail: ");
        ll.delete(5);
        ll.printList();
        System.out.println("size: " + ll.size);

        System.out.println("Deleting middle of list: ");
        ll.delete(3);
        ll.printList();
        System.out.println("size: " + ll.size);

        System.out.println("Deleting non-existent element: ");
        ll.delete(5);
        ll.printList();
        System.out.println("size: " + ll.size);

        System.out.println("Deleting all elements: ");
        ll.delete(2);
        ll.delete(4);
        ll.printList();
        System.out.println("size: " + ll.size);
    }

    public static LinkedListDs createTestLinkedList() {
        LinkedListDs ll = new LinkedListDs();

        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);

        return ll;
    }
}

class LinkedListDs {
    public int size = 0;
    private node head = null;
    private node tail = null;

    public void add(int value) {
        size++;
        if (head == null) {
            head = new node(value);
            tail = head;
        }
        else {
            node newNode = new node(value);
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void delete(int value) {
//         pn    dn   nn
//        1 -> 2 -> 3
        // case deleting head:
            // point head to next.next
        // case deleting middle node:
            // point the previous node to the node after the middle node
        // case deleting the tail:
            // point the previous node to null
        // case when list is null
        // case when we cannot find the node to delete

        if (head == null) {
            return;
        }

        // start with the head first
        if (head.value == value) {
            size--;
            head = head.next;
            return;
        }

        node previous = head;
        while (previous.next != null) {
            if (previous.next.value == value) {
                size--;
                previous.next = previous.next.next;
                break;
            }
            previous = previous.next;
        }
    }

    public void printList() {
        node tempPtr = head;
        while (tempPtr != null) {
            System.out.print(tempPtr.value + " ");
            tempPtr = tempPtr.next;
        }
        System.out.println("");
    }
}

class node {
    public int value;
    node next;
    node(int x) { value = x; }
}
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
        runSearchTests();
    }

    private static void runSearchTests() {
        LinkedListDs ll = createTestLinkedList();

        // Search for head
        System.out.print("Search for head: ");
        node foundHead = ll.find(1);
        if (foundHead.value == 1) {
            System.out.println("passed");
        }
        else {
            System.out.println("failed");
        }

        // Search for tail
        System.out.print("Search for tail: ");
        node foundTail = ll.find(5);
        if (foundTail.value == 5) {
            System.out.println("passed");
        }
        else {
            System.out.println("failed");
        }

        // Search for mid list item
        System.out.print("Search for mid item: ");
        node foundMid = ll.find(3);
        if (foundMid.value == 3) {
            System.out.println("passed");
        }
        else {
            System.out.println("failed");
        }

        // Search for non-existent item
        System.out.print("Search for non-existent item: ");
        node foundNon = ll.find(6);
        if (foundNon == null) {
            System.out.println("passed");
        }
        else {
            System.out.println("failed");
        }
    }

    private static void runDeleteTests() {
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

    private static LinkedListDs createTestLinkedList() {
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

        node newNode = new node(value);
        tail.next = newNode;
        tail = newNode;
    }

    public void delete(int value) {

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

    public node find(int value) {
        node curr = head;
        while (curr != null) {
            if (curr.value == value) {
                break;
            }
            curr = curr.next;
        }

        return curr;
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
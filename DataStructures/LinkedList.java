/*
Linked list operations:
 - Access (given an index)
 - Delete
 - Search
 - Add
 */

public class LinkedList {
    public static void main(String[] args) {
        // Sample linked list: 1 -> 2 -> 3 -> 4 -> 5
        // Create methods to modify a single node

        LinkedListDs ll = new LinkedListDs();
        ll.add(1);
        ll.add(2);
        ll.printList();
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

    public void printList() {
        node tempPtr = head;
        while (tempPtr != null) {
            System.out.print(tempPtr.value + " ");
            tempPtr = tempPtr.next;
        }
    }
}

class node {
    public int value;
    node next;
    node(int x) { value = x; }
}
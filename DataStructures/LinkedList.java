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

        System.out.println("New list created: ");
        LinkedListDs ll = new LinkedListDs();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        ll.printList();
        System.out.println("size: " + ll.size);

        System.out.println("Deleting head: ");
        ll.delete(1);
        ll.printList();
        System.out.println("size: " + ll.size);
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
        node temp = head;
        while(temp != null) {
            if (temp.value == value) {
                size--;
                head = temp.next;
            }
            temp = temp.next;
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
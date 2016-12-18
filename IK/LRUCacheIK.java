import java.util.HashMap;

/**
 * Created by kevinto on 12/16/16.
 */
public class LRUCacheIK {
    public static void main(String[] args) {
        LRUCacheIK ik = new LRUCacheIK();
        LRUCache lc = ik.new LRUCache(2);
        lc.set(1, 1);
        lc.set(2, 2);
        System.out.println(lc.get(1));
        System.out.println(lc.get(2));
        System.out.println(lc.get(3));
        lc.set(3, 3);
        System.out.println(lc.get(1));
        lc.set(2, 100);
        lc.set(4, 4);
        System.out.println(lc.get(3));
        System.out.println(lc.get(2));
    }

    class LRUCache {
        private int size;
        private int capacity;
        private Node tail;
        private Node head;
        private HashMap<Integer, Node> map;

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            head = new Node(-1, -1);
            tail = new Node(-2, -2);
            head.next = tail;
            tail.prev = head;
            map = new HashMap<>();
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }

            Node curr = map.get(key);

            // Remove curr from linked list
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;

            // Move curr to the front of the list
            curr.next = head.next;
            curr.prev = head;
            head.next.prev = curr;
            head.next = curr;

            return curr.val;
        }

        public void set(int key, int value) {
            if (!map.containsKey(key)) {
                if (size == capacity) {
                    removeOldest();
                }
                addNew(key, value);
            } else {
                updateExisting(key, value);
            }
        }

        private void updateExisting(int key, int value) {
            Node curr = map.get(key);
            curr.val = value;

            // Remove from list
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;

            // Add to the front of list
            curr.next = head.next;
            head.next.prev = curr;
            head.next = curr;
        }

        private void addNew(int key, int value) {
            Node newNode = new Node(key, value);
            map.put(key, newNode);

            newNode.next = head.next;
            newNode.prev = head;
            head.next.prev = newNode;
            head.next = newNode;
            size++;
        }


        private void removeOldest() {
            Node last = tail.prev;
            if (last == head) {
                return;
            }

            // Remove from linked list
            last.prev.next = tail;
            tail.prev = last.prev;

            map.remove(last.key);
            size--;
        }

        class Node {
            Node next;
            Node prev;
            int val;
            int key;

            Node(int k, int v) {
                this.val = v;
                this.key = k;
            }
        }
    }
}

import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {
        LruImpl lru = new LruImpl(3);
        lru.set(1, "a");
        lru.set(2, "b");
        lru.set(3, "c");
        lru.set(4, "d");
        lru.get(2);
        lru.set(5, "e");

        System.out.println(lru.get(2));
        System.out.println(lru.get(3));
    }

    static class LruImpl implements LRU{
        private int capacity;
        private int size;
        private HashMap<Integer, Node> map;
        private Node head;
        private Node tail;

        public LruImpl(int cap) {
            this.capacity = cap;
            this.size = 0;

            map = new HashMap<>();
            head = new Node("head");
            tail = new Node("tail");

            head.next = tail;
            tail.prev = head;
        }

        public String get(int key) {
            if (map.containsKey(key)) {
                Node curr = map.get(key);
                moveToFront(curr);
                return curr.val;
            } else {
                return "not found...";
            }
        }

        public void set (int key, String val) {
            if (map.containsKey(key)) {
                Node curr = map.get(key);
                curr.val = val;
                moveToFront(curr);
            } else {
                if (size == capacity) {
                    ageOff();
                }

                Node newNode = new Node(key, val);
                map.put(key, newNode);
                addToFront(newNode);
                size++;
            }
        }

        private void moveToFront(Node node) {
            // Take out of current position
            node.prev.next = node.next;
            node.next.prev = node.prev;

            addToFront(node);
        }

        private void ageOff() {
            if (size == 0) {
                return;
            } else if (size < capacity) {
                return;
            }

            size--;
            Node toDel = tail.prev;
            tail.prev = toDel.prev;

            map.remove(toDel.key);
        }

        private void addToFront(Node node) {
            node.prev = head;
            node.next = head.next;

            head.next.prev = node;
            head.next = node;
        }

        static class Node {
            Node next;
            Node prev;
            int key;
            String val;

            Node (int key, String val) {
                this.val = val;
                this.key = key;
            }

            Node (String val) {
                this.val = val;
            }
        }
    }

    interface LRU {
        // get(key) - Get the value (will always be positive)
        //            of the key if the key exists in the cache, otherwise return -1.
        String get(int key);

        // set(key, value) - Set or insert the value if the key is not already present.
        //                   When the cache reached its capacity, it should invalidate the
        //                   least recently used item before inserting a new item.
        void set (int key, String val);
    }
}


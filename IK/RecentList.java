import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kevinto on 12/15/16.
 * add(String id) - adds a string
 * getMostRecent() - returns the top 4. take care of not showing duplicates
 */
public class RecentList {
    public static void main(String[] args) {
        RecentListImpl r = new RecentListImpl();
        r.add("a");
        r.add("b");
        r.add("c");
        r.add("d");
        r.add("e");
        r.add("d");
        System.out.println(r.getMostRecent());

        RecentListImpl r1 = new RecentListImpl();
        r1.add("a");
        r1.add("b");
        System.out.println(r1.getMostRecent());
    }

    static class RecentListImpl implements RecentListInterFace {
        private Node head;
        private Node tail;
        private int linkedListSize;
        private HashMap<String, Node> map;
        private ArrayList<String> stack;
        private final int MAX_SIZE = 4;

        public RecentListImpl() {
            head = new Node("head");
            tail = new Node("tail");
            head.next = tail;
            tail.prev = head;

            map = new HashMap<>();
            stack = new ArrayList<>();
        }

        public void add(String id) {
            stack.add(id);
            linkedListAdd(id);
        }

        public List<String> getMostRecent() {
            List<String> res = new ArrayList<>();

            Node curr = head.next;
            while (curr != tail) {
                res.add(curr.val);
                curr = curr.next;
            }
            return res;
        }

        private void linkedListAdd(String id) {
            if (map.containsKey(id)) {
                // Move the found node to the beginning of the list
                Node nodeToMove = map.get(id);
                nodeToMove.prev.next = nodeToMove.next;
                nodeToMove.next.prev = nodeToMove.prev;
                nodeToMove.next = head.next;
                nodeToMove.prev = head;
                head.next = nodeToMove;
            } else {
                // Add new node to the head
                Node newNode = new Node(id);
                newNode.next = head.next;
                newNode.prev = head;
                head.next.prev = newNode;
                head.next = newNode;
                map.put(id, newNode);
                linkedListSize++;

                if (linkedListSize > MAX_SIZE) {
                    // remove the extra node at the end.
                    String keyToRemove = tail.prev.val;
                    map.remove(keyToRemove);
                    tail.prev = tail.prev.prev;
                    tail.prev.next = tail;
                }
            }
        }

        private static class Node {
            Node next;
            Node prev;
            String val;

            Node (String s) {
                this.val = s;
            }
        }
    }
}

interface RecentListInterFace {
    void add(String id);
    List<String> getMostRecent();
}

/* Potentially cleaner solution:
static class MriImpl implements MRI {
        // add(String id) - adds a string
        // getMostRecent() - returns the top 4. take care of not showing duplicates

        private HashMap<String, Node> map;
        private Node head;
        private Node tail;
        private final int MAX_SIZE = 4;

        public MriImpl() {
            map = new HashMap<>();
            head = new Node("head");
            tail = new Node("tail");

            head.next = tail;
            tail.prev = head;
        }

        public void add(String str) {
            if (map.containsKey(str)) {
                moveToFront(map.get(str));
            } else {
                Node newNode = new Node(str);
                map.put(str, newNode);
                addNode(newNode);
            }
        }

        public String getMostRecent() {
            System.out.println("most recent: ");
            int temp = MAX_SIZE;
            Node curr = head.next;

            while (curr != null && curr != tail && temp > 0) {
                System.out.println(curr.val);
                temp--;
                curr = curr.next;
            }

            return "";
        }

        private void moveToFront(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void addNode(Node node) {
            node.prev = head;
            node.next = head.next;

            head.next.prev = node;
            head.next = node;
        }

        private class Node {
            Node next;
            Node prev;
            String val;

            Node(String v) {
                this.val = v;
            }
        }
    }
 */

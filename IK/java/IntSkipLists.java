import java.util.Random;

/**
 * Created by kevinto on 12/22/16.
 */
public class IntSkipLists {
    public static void main(String[] args) {
        IntSkipLists sl = new IntSkipLists();
        sl.insert(1);
        System.out.println(sl.contains(1));
        System.out.println(sl.contains(2));
    }

    private class Node {
        public Node[] Next;
        public int Value;

        public Node(int value, int level) {
            this.Value = value;
            Next = new Node[level];
        }
    }

    private Node head = new Node(0, 33); // 33 is max number of levels
    private Random rand = new Random();
    private int levels = 1;

    public void insert(int value) {
        // Determine the level of the new node. Generate a random number R. The number of
        // 1-bits before we encounter the first 0-bit is the level of the node. Since R is
        // 32-bit, the level can be at most 32.
        int level = 0;
        for (int R = rand.nextInt(); (R & 1) == 1; R >>= 1) {
            level++;
            if (level == levels) {
                levels++;
                break;
            }
        }

        // Insert this node into the skip list
        Node newNode = new Node(value, level + 1);
        Node cur = head;
        for (int i = levels - 1; i >= 0; i--) {
            for (; cur.Next[i] != null; cur = cur.Next[i]) {
                if (cur.Next[i].Value > value) {
                    break;
                }
            }

            if (i <= level) {
                newNode.Next[i] = cur.Next[i];
                cur.Next[i] = newNode;
            }
        }
    }

    public boolean contains(int value) {
        Node cur = head;
        for (int i = levels - 1; i >= 0; i--) {
            for (; cur.Next[i] != null; cur = cur.Next[i]) {
                if (cur.Next[i].Value > value) break;
                if (cur.Next[i].Value == value) return true;
            }
        }
        return false;
    }

    public boolean remove(int value) {
        Node cur = head;

        boolean found =false;
        for (int i = levels - 1; i >= 0; i--) {
            for (; cur.Next[i] != null; cur = cur.Next[i]) {
                if (cur.Next[i].Value == value) {
                    found = true;
                    cur.Next[i] = cur.Next[i].Next[i];
                    break;
                }
                if (cur.Next[i].Value > value) {
                    break;
                }
            }
        }

        return found;
    }
}

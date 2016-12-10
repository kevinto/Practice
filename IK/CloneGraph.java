import java.util.*;
import java.util.LinkedList;

/**
 * Created by kevinto on 12/10/16.
 */
public class CloneGraph {
    public static void main(String args[] ) throws Exception {
        Node root = new Node(1);
        Node child1 = new Node(2);
        Node child2 = new Node(3);

        root.neighbors.add(child1);
        child1.neighbors.add(child2);
        Node clone = cloneGraph(root);

        return;
    }

    static class Node {
        List<Node> neighbors;
        int val;

        Node (int x) {
            val = x;
            neighbors = new ArrayList<>();
        }
    }

    public static Node cloneGraph(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList();
        queue.offer(root);
        Node newRoot = new Node(root.val);
        HashMap<Node, Node> oldNewMap = new HashMap<>();
        oldNewMap.put(root, newRoot);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (Node neighbor : curr.neighbors) {
                if (oldNewMap.containsKey(neighbor)) {
                    oldNewMap.get(curr).neighbors.add(oldNewMap.get(neighbor));
                } else {
                    Node copy = new Node(neighbor.val);
                    oldNewMap.put(neighbor, copy);
                    oldNewMap.get(curr).neighbors.add(copy);
                    queue.offer(neighbor);
                }
            }
        }
        return newRoot;
    }
}

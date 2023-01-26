import java.util.*;
import java.util.LinkedList;

/**
 * Created by kevinto on 12/10/16.
 * http://www.programcreek.com/2012/12/leetcode-clone-graph-java/
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

                    // Keeping the enqueue in here is necessary because if the copy
                    // does not exist in our map then we know we havent encountered
                    // the current child before. If we have not encountered the current
                    // child before then we can put it in queue for processing.
                    queue.offer(neighbor);
                }
            }
        }
        return newRoot;
    }

    public static boolean isIdentical(Node root1, Node root2) {
        HashSet<Node> visited = new HashSet<>();
        return isIdentical(root1, root2, visited);
    }

    public static boolean isIdentical(Node root1, Node root2, HashSet<Node> visited) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        } else if (root1.neighbors.size() != root2.neighbors.size()) {
            return false;
        } else if (!allChildrenMatch(root1, root2)) {
            return false;
        }

        boolean matches = true;
        visited.add(root1);
        for (Node neighbor1 : root1.neighbors) {
            for (Node neighbor2 : root2.neighbors) {
                if (!visited.contains(neighbor1) && neighbor1.val == neighbor2.val) {
                    matches &= isIdentical(neighbor1, neighbor2);
                }

                if (!matches) {
                    return false;
                }
            }
        }

        return matches;
    }

    private static boolean allChildrenMatch(Node root1, Node root2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (Node child : root1.neighbors) {
            set1.add(child.val);
        }

        for (Node child : root2.neighbors) {
            if (!set1.contains(child.val)) {
                return false;
            }
        }

        return true;
    }
}

import java.util.*;
import java.util.LinkedList;

public class Solution {
    public static void main(String args[] ) throws Exception {

        Node root = new Node(1);
        Node child1 = new Node(2);
        Node child2 = new Node(3);

        root.neighbors.add(child1);
        child1.neighbors.add(child2);
        Node clone = cloneGraph(root);

        return;
    }

    public static ArrayList<Integer> mergeSorted (ArrayList<ArrayList<Integer>> sortedLists) {
        ArrayList<Integer> result = new ArrayList<>();
        int numSortedLists = sortedLists.size();
        PriorityQueue<HeapNode> pq = new PriorityQueue<>(numSortedLists);

        for (int listNum = 0; listNum < numSortedLists; listNum++) {
            if (sortedLists.get(listNum) == null) {
                continue;
            }
            int pos = 0;
            int elementValue = sortedLists.get(listNum).get(pos);
            HeapNode curr = new HeapNode(sortedLists.get(listNum).get(pos), listNum, pos);
            pq.offer(curr);
        }

        while (!pq.isEmpty()) {
            HeapNode curr = pq.poll();

            if (curr.elementPos < sortedLists.get(curr.listNum).size() - 1) {
                pq.offer(new HeapNode(sortedLists.get(curr.listNum).get(curr.elementPos + 1), curr.listNum, curr.elementPos + 1));
            }

            result.add(curr.val);
        }

        return result;
    }

    private static class HeapNode implements Comparable<HeapNode> {
        int listNum;
        int elementPos;
        int val;

        HeapNode(int x, int listNum, int elementPos) {
            this.val = x;
            this.listNum = listNum;
            this.elementPos = elementPos;
        }

        @Override
        public int compareTo(HeapNode other) {
            return this.val - other.val;
        }
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
        HashMap<Node, Node> oldNewMap = new HashMap<>();
        Node newRoot = null;
        HashSet<Node> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            Node newNode = new Node(curr.val);
            if (newRoot == null) {
                newRoot = newNode;
            }

            oldNewMap.put(curr, newNode);

            for (Node neighbor : curr.neighbors) {
                if (oldNewMap.containsKey(neighbor)) {
                    newNode.neighbors.add(oldNewMap.get(neighbor));
                } else {
                    oldNewMap.put(neighbor, new Node(neighbor.val));
                    newNode.neighbors.add(oldNewMap.get(neighbor));
                }

                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                }
            }
        }
        return newRoot;
    }
}


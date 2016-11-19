import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by kevinto on 11/19/16.
 */
public class MergeKSortedArrays {
    public static void main(String[] args) {
        int[][] test1 = {
                {1, 2},
                {3, 4}
        };
        int[] result = mergearrays(test1);
        System.out.println(Arrays.toString(result));
    }

    static int[] mergearrays(int[][] lists) {
        int[] results = new int[0];
        if (lists == null || lists.length == 0) {
            return results;
        }
        int numLists = lists.length;
        int elePerList = lists[0].length;
        results = new int[numLists * elePerList];

        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
        initPq(pq, lists);

        int finalPos = 0;
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            results[finalPos++] = curr.val;

            if (curr.listPos  + 1 < elePerList) {
                Node nextNode = new Node(lists[curr.listNum][curr.listPos + 1], curr.listNum, curr.listPos + 1);
                pq.offer(nextNode);
            }
        }

        return results;
    }

    static void initPq(PriorityQueue<Node> pq, int[][] lists) {
        for (int i = 0; i < lists.length; i++) {
            pq.offer(new Node(lists[i][0], i, 0));
        }
    }

    static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node n1, Node n2) {
            return n1.val - n2.val;
        }
    }

    static class Node {
        int val;
        int listNum;
        int listPos;

        Node(int inputVal, int inputListNum, int inputlistPos) {
            val = inputVal;
            listNum = inputListNum;
            listPos = inputlistPos;
        }
    }
}

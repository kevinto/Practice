import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by kevinto on 12/20/16.
 */
public class TopologicalSortOwn {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            adjList.add(new ArrayList<>());
        }
        adjList.get(5).add(2);
        adjList.get(5).add(0);
        adjList.get(4).add(0);
        adjList.get(4).add(1);
        adjList.get(2).add(3);
        adjList.get(3).add(1);

        ArrayList<Integer> result = topSort(adjList);
        System.out.println(result);
    }

    public static ArrayList<Integer> topSort(ArrayList<ArrayList<Integer>> adjList) {
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[adjList.size()];

        for (int i = 0; i < adjList.size(); i++) {
            if (!visited[i]) {
                topSort(adjList, i, res, visited);
            }
        }
        Collections.reverse(res);
        return res;
    }

    private static void topSort(ArrayList<ArrayList<Integer>> adjList, int node, ArrayList<Integer> res, boolean[] visited) {
        if (node >= adjList.size() || visited[node]) {
            return;
        }

        visited[node] = true;
        for (int neighbor : adjList.get(node)) {
            topSort(adjList, neighbor, res, visited);
        }
        res.add(node);
    }
}

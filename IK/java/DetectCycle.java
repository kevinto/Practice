import java.util.ArrayList;

/**
 * Created by kevinto on 12/4/16.
 */
public class DetectCycle {
    public static void main(String args[] ) throws Exception {
        runGraphWithCycleTest();
        runGraphWithNoCycleTest();
    }

    public static boolean cycleExists(ArrayList<ArrayList<Integer>> adjList) {
        boolean[] visited = new boolean[adjList.size()];
        boolean[] currStack = new boolean[adjList.size()];

        for (int i = 0; i < adjList.size(); i++) {
            if (!visited[i] && cycleExists(adjList, i, visited, currStack)) {
                return true;
            }
        }

        return false;
    }

    public static boolean cycleExists(ArrayList<ArrayList<Integer>> adjList, int node, boolean[] visited, boolean[] currStack) {
        if (currStack[node]) {
            return true;
        }
        currStack[node] = true;

        for (int child : adjList.get(node)){
            if (!visited[child] && cycleExists(adjList, child, visited, currStack)) {
                return true;
            }
        }

        visited[node] = true;
        currStack[node] = false;
        return false;

        // Alternative implementation of cycle detection that does the same thing.
//        visited[node] = true;
//        currStack[node] = true;
//
//        for (int child : adjList.get(node)){
//            if (!visited[child] && cycleExists(adjList, child, visited, currStack)) {
//                return true;
//            } else if (currStack[child]) {
//                return true;
//            }
//        }

//        currStack[node] = false;
//        return false;
    }
    private static void runGraphWithCycleTest() {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            adjList.add(new ArrayList<Integer>());
        }

        adjList.get(0).add(1);
        adjList.get(1).add(2);
        adjList.get(2).add(3);
        adjList.get(3).add(4);
        adjList.get(3).add(5);
        adjList.get(5).add(2);

        boolean result = cycleExists(adjList);
        System.out.println("Contains cycle test: passed = " + result);
    }

    private static void runGraphWithNoCycleTest() {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            adjList.add(new ArrayList<Integer>());
        }

        adjList.get(0).add(1);
        adjList.get(1).add(2);
        adjList.get(2).add(3);
        adjList.get(3).add(4);

        boolean result = !cycleExists(adjList) ? true : false;
        System.out.println("Contains no cycle test: passed = " + result);
    }
}

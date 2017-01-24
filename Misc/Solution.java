import java.util.*;

public class Solution {
    public static void main(String[] args) {
        String[] words = {"zk", "ze", "be", "e"};
        findOrder(words);
        return;
    }

    //private List<HashSet<Character>> adjList; // Bad choice because we need to represent letters here.
    //  not numbers.
    private static HashMap<Character, HashSet<Character>> adjList;

    public static String findOrder(String[] dict) {
        if (dict == null || dict.length == 0) {
            return "";
        }

        generateGraph(dict);
        //return topSort();
        return "";
    }

    private static void generateGraph(String[] dict) {
        int first, second;
        adjList = new HashMap<>();

        for (int dictIdx = 0; dictIdx < dict.length - 1; dictIdx++) {
            first = 0;
            second = 0;

            if (dict[dictIdx].charAt(first) != dict[dictIdx + 1].charAt(second)) {
                addEdge(dict[dictIdx].charAt(first), dict[dictIdx + 1].charAt(second));
                continue;
            }

            while (first < dict[dictIdx].length() && second < dict[dictIdx + 1].length()
                    && dict[dictIdx].charAt(first) == dict[dictIdx + 1].charAt(second)) {

                first++;
                second++;

                if (first < dict[dictIdx].length() && second < dict[dictIdx + 1].length()
                        && dict[dictIdx].charAt(first) != dict[dictIdx + 1].charAt(second)) {

                    addEdge(dict[dictIdx].charAt(first), dict[dictIdx + 1].charAt(second));
                    break;
                }
            }

        }
    }

    private static void addEdge(char begin, char end) {
        if (!adjList.containsKey(begin)) {
            adjList.put(begin, new HashSet<>());
        }

        HashSet<Character> set = adjList.get(begin);
        set.add(end);
    }
}


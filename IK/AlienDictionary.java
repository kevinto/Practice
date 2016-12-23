import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by kevinto on 12/22/16.
 */
public class AlienDictionary {
    public static void main(String[] args) {
        String[] words1 = {"baa", "abcd", "abca", "cab", "cad"};
        String result1 = findOrder(words1);
        System.out.println(result1);
    }

    /*
        1) Create a graph g with number of vertices equal to the size
        of alphabet in the given alien language. For example, if the
        alphabet size is 5, then there can be 5 characters in words.
        Initially there are no edges in graph.

        2) Do following for every pair of adjacent words in given sorted array.
        …..a) Let the current pair of words be word1 and word2. One by one compare
              characters of both words and find the first mismatching characters.
        …..b) Create an edge in g from mismatching character of word1 to that of word2.

        3) Print topological sorting of the above created graph.
     */

    private static HashMap<Character, HashSet<Character>> graph;
    private static String findOrder(String[] dict) {
        if (dict.length < 2) {
            return "";
        }

        graph = new HashMap<>();
        loadGraph(dict);
        return topSort();
    }

    private static void loadGraph(String[] dict) {
        for (int i = 1; i < dict.length; i++) {
            String firstStr = dict[i - 1];
            String secondStr = dict[i];
            int firstPtr = 0;
            int secondPtr = 0;
            int firstLen = firstStr.length();
            int secondLen = secondStr.length();

            while (firstPtr < firstLen && secondPtr < secondLen
                    && firstStr.charAt(firstPtr) == secondStr.charAt(secondPtr)) {
                firstPtr++;
                secondPtr++;
            }

            if (firstPtr < firstLen && secondPtr < secondLen
                    && firstStr.charAt(firstPtr) != secondStr.charAt(secondPtr)) {
                char frontChar = firstStr.charAt(firstPtr);
                char backChar = secondStr.charAt(secondPtr);
                if (!graph.containsKey(frontChar)) {
                    graph.put(frontChar, new HashSet<>());
                }
                graph.get(frontChar).add(backChar);

                // Just in case the back char only has edges pointing towards it.
                if (!graph.containsKey(backChar)) {
                    graph.put(backChar, new HashSet<>());
                }
            }
        }
    }

    private static String topSort() {
        HashSet<Character> visited = new HashSet<>();
        StringBuilder sbResult = new StringBuilder();

        for (char key : graph.keySet()) {
            if (!visited.contains(key)) {
                topSort(visited, sbResult, key);
            }
        }

        return sbResult.reverse().toString();
    }

    private static void topSort(HashSet<Character> visited, StringBuilder sbResult, char node) {
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);

        for (char child : graph.get(node)) {
            topSort(visited, sbResult, child);
        }
        sbResult.append(node);
    }
}

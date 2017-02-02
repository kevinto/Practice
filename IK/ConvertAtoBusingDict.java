import java.util.*;
import java.util.LinkedList;

/**
 * Created by kevinto on 12/22/16.
 */
public class ConvertAtoBusingDict {
    public static void main(String[] args) {
        String[] words = {"cat", "bat", "hat", "bad", "had"};
        String s1 = "bat";
        String s2 = "had";
        System.out.println(getPath(words, s1, s2));
    }

    private static HashMap<String, HashSet> graph;
    private static List<String> getPath(String[] words, String s1, String s2) {
        loadGraph(words);
        int minSteps = getMinSteps(s1, s2);

        if (minSteps == -1) {
            System.out.println("Second word cannot be reached");
        }

        List<String> path = dfs(s1, s2, minSteps);
        return path;
    }

    private static List<String> dfs(String start, String end, int movesLeft) {
        List<String> path = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();
        path.add(start);
        dfs(start, end, path, movesLeft, visited);
        return path;
    }

    private static boolean dfs(String currentNode, String end, List<String> path, int movesLeft, HashSet<String> visited) {
        if (movesLeft == 0 && currentNode.equals(end)) {
            return true;
        } else if (movesLeft <= 0) {
            return false;
        }
        visited.add(currentNode);

        HashSet<String> neighbors = graph.get(currentNode);
        for (String neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                path.add(neighbor);
                if (dfs(neighbor, end, path, movesLeft - 1, visited)) {
                    return true;
                }
                path.remove(path.size() - 1);
            }
        }

        return false;
    }

    private static int getMinSteps(String start, String end) {
        int minSteps = 0;
        boolean foundEnd = false;
        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList();
        queue.add(start);

        while (!queue.isEmpty()) {
            int levelCt = queue.size();
            for (int i = 0; i < levelCt; i++) {
                String curr = queue.remove();
                visited.add(curr);

                if (curr.equals(end)) {
                    foundEnd = true;
                    return minSteps;
                }

                HashSet<String> neighbors = graph.get(curr);
                for (String neighbor : neighbors){
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                    }
                }
            }

            minSteps++;
        }

        if (foundEnd) {
            return minSteps;
        } else {
            return -1;
        }
    }

    private static void loadGraph(String[] words) {
        graph = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (differBy1Character(words[i], words[j])) {
//                    System.out.println("found it: " + words[i] + ", " + words[j]);
                    if (!graph.containsKey(words[i])) {
                        graph.put(words[i], new HashSet<>());
                    }

                    if (!graph.containsKey(words[j])) {
                        graph.put(words[j], new HashSet<>());
                    }

                    graph.get(words[i]).add(words[j]);
                    graph.get(words[j]).add(words[i]);
                }
            }
        }
    }

    // We are assuming all lower case letters
    private static boolean differBy1Character(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        int[] map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            map[s1.charAt(i) - 'a']++;
        }


        for (int i = 0; i < s2.length(); i++) {
            map[s2.charAt(i) - 'a']--;
        }

        int countNegOne = 0;
        int countPosOne = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] == 1) {
                countPosOne++;
            } else if (map[i] == -1) {
                countNegOne++;
            } else if (map[i] != 0) {
                return false;
            }
        }
        return (countPosOne == 1) && (countNegOne == 1);
    }

    // This takes care of words that differ in length by 1 letter
    private static boolean wordsDiffBy1Ver2(String s1, String s2) {
        if (Math.abs(s1.length() - s2.length()) > 1) {
            return false;
        }

        int[] freq = new int[256];

        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i)]++;
        }

        for (int i = 0; i < s2.length(); i++) {
            freq[s2.charAt(i)]--;
        }

        int posOneCt = 0;
        int negOneCt = 0;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] == 0) {
                continue;
            } else if (freq[i] == 1) {
                posOneCt++;
            } else if (freq[i] == -1) {
                negOneCt++;
            } else {
                return false;
            }
        }

        if (Math.abs(s1.length() - s2.length()) == 1) {
            // One letter extra in one of the words. That means
            // you should only have 1 letter difference in your
            // frequency array.
            return (posOneCt == 1 && negOneCt == 0)
                    || (posOneCt == 0 && negOneCt == 1);
        } else {
            return posOneCt == 1 && negOneCt == 1;
        }
    }
}

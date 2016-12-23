import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by kevinto on 12/22/16.
 */
public class ConvertAtoBusingDict {
    public static void main(String[] args) {
//        String[] words = {"cat", "bat", "hat", "bad", "had"};
        String[] words = {"bat", "bad", "bcc"};
        String s1 = "bat";
        String s2 = "had";
        System.out.println(getPath(words, s1, s2));
    }

    private static HashMap<String, HashSet> graph;
    private static List<String> getPath(String[] words, String s1, String s2) {
        loadGraph(words);
        return null;
    }

    private static void loadGraph(String[] words) {
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (differBy1Character(words[i], words[j])) {
//                    System.out.println("found it: " + words[i] + ", " + words[j]);
                    if (!graph.containsKey(words[i])) {
                        pr
                    }

                    if (!graph.containsKey(words[j])) {

                    }
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
            }
        }
        return countPosOne == countNegOne;
    }

}

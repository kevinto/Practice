/**
 * Created by Kevin on 11/2/16.
 */
import java.util.*;

public class WordBreak {
    public static void main(String[] args) {
        String[] dict1 = { "4", "app", "apple", "let", "pie" };
        System.out.println(Arrays.toString(wordBreak("applepie", dict1)));

        String[] dict2 = { "cat", "cats", "and", "sand", "dog" };
        System.out.println(Arrays.toString(wordBreak("catsanddog", dict2)));
    }

    static String[] wordBreak(String word, String[] strDict) {
        HashSet<String> dict = genDict(strDict);

        boolean res = wordCanBeEvenlyBroken(word, dict);
        List<String>[] dpTable = possibleWords(word, dict);
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> path = new ArrayList<>();
        dfs(dpTable, 0, path, result);
        String[] resultArr = new String[result.size()];

        return result.toArray(resultArr);
    }

    private static void dfs(List<String>[] dpTable, int start, ArrayList<String> path, ArrayList<String> result) {
        if (dpTable[start] == null) {
            return;
        } else if (start == dpTable.length - 1) {
            for (String word : path) {
                result.add(word);
            }
            return;
        }

        for (String word : dpTable[start]) {
            path.add(word);
            dfs(dpTable, word.length() + start, path, result);
            path.remove(path.size() - 1);
        }
    }

    // This implementation puts a true value at the start of the word. Why does this
    // implementation save on duplicate work?
    //  -- This current code is o(n^2). This is because for every start position we
    //     are trying to find all the possible end positions.
    //  -- With the recursive solution, we encounter the same start positions multiple
    //     times in a row. This happens when each for loop level is a word. This causes
    //     us to start at the same position multiple times, potentially. CHECK REFERENCE PROBLEM NOTEBOOK
    static List<String>[] possibleWords(String word, HashSet<String> dict) {
        int wordLen = word.length();
        List<String>[] dp = new ArrayList[wordLen + 1];
        dp[wordLen] = new ArrayList<>(); // Where the last position list is CREATED!

        for (int dpIdx = wordLen; dpIdx >= 0; dpIdx--) {
            if (dp[dpIdx] != null) {
                for (int i = dpIdx - 1; i >= 0; i--) {
                    String sub = word.substring(i, dpIdx);
                    if (dict.contains(sub)) {
                        if (dp[i] == null) {
                            dp[i] = new ArrayList<>();
                        }
                        dp[i].add(sub);
                    }
                }
            }
        }
        return dp;
    }

    // This implementation puts a true value at the start of the word.
    static boolean wordCanBeEvenlyBroken(String word, HashSet<String> dict) {
        int len = word.length();
        boolean[] dp = new boolean[len + 1];
        dp[len] = true;

        for (int dpIdx = len - 1; dpIdx >= 0; dpIdx--) {
            if (dp[dpIdx]) {
                continue;
            }

            for (int i = dpIdx; i >= 0; i--) {
                String sub = word.substring(i, dpIdx + 1);
                if (dict.contains(sub) && dp[dpIdx + 1]) {
                    dp[i] = true;
                }
            }

        }

        return dp[0];
    }

    static HashSet<String> genDict(String[] strDict) {
        HashSet<String> dict = new HashSet<>();
        for (String val : strDict) {
            dict.add(val);
        }
        return dict;
    }



}

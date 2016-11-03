/**
 * Created by Kevin on 11/2/16.
 */
import java.util.*;

public class WordBreak {
    public static void main(String[] args) {
        String[] dict = { "4", "app", "apple", "let", "pie" };

//        System.out.println(wordBreak("abc", dict));
        wordBreak("applepie", dict);
    }

    static String[] wordBreak(String word, String[] strDict) {
        HashSet<String> dict = genDict(strDict);

        // printArr(result);
        ArrayList<String> result = new ArrayList<>();
        System.out.print(wordCanBeEvenlyBroken(word, dict, 0, result));
//        wordBreakHelper(word, dict, 0, result);
        String[] resultArr = new String[result.size()];
        return result.toArray(resultArr);
    }

    static boolean wordCanBeEvenlyBroken(String word, HashSet<String> dict, int start, ArrayList<String> result) {
        int len = word.length();
        boolean[] dp = new boolean[len + 1];
        dp[len] = true;

        for (int dpIdx = len - 1; dpIdx >= 0; dpIdx--) {
            for (int i = dpIdx; i >= 0; i--) {
                String sub = word.substring(i, dpIdx + 1);
                if (dict.contains(sub) && dp[dpIdx + 1]) {
                    dp[i] = true;
                }
            }

        }

        return dp[0];
    }

    static void printArr(ArrayList<String> arr) {
        for (String val : arr) {
            System.out.println(val);
        }
    }

    static HashSet<String> genDict(String[] strDict) {
        HashSet<String> dict = new HashSet<>();
        for (String val : strDict) {
            dict.add(val);
        }
        return dict;
    }



}

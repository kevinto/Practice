/**
 * Created by kevinto on 12/20/16.
 */
import java.util.*;
import java.util.LinkedList;

public class LongestSubstringWithKDistinctCharacters {
    public static void main(String[] args) {
        String s1 = "eceba";
        System.out.println(longestLengthSubWithHashAndQueue(s1));

        String s2 = "ecebaaab";
        System.out.println(longestLengthSubWithHashAndQueue(s2));
    }

    private static int longestLengthSubWithHashAndQueue(String str) {
        Queue<Character> queue = new LinkedList();
        HashMap<Character, Integer> map = new HashMap<>();
        int len = str.length();
        int maxLen = 0;

        for (int i = 0; i < len; i++) {
            char curr = str.charAt(i);
            if (map.containsKey(curr)) {
                map.put(curr, map.get(curr) + 1);
            } else if (map.size() <= 1) {
                map.put(curr, 1);
            } else {
                char beginningOfQueue = queue.remove();
                while (map.size() >= 2) {
                    if (map.get(beginningOfQueue) <= 1) {
                        map.remove(beginningOfQueue);
                        continue;
                    } else {
                        map.put(beginningOfQueue, map.get(beginningOfQueue) - 1);
                    }
                    beginningOfQueue = queue.remove();
                }
            }
            queue.add(curr);
            maxLen = Math.max(maxLen, queue.size());
        }
        return maxLen;
    }
}

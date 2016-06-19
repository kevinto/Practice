package ch8DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 6/18/16.
 */
public class PermWDups {
    public static void main(String[] args) {
        String str = "aabc";

        Solution solution = new Solution();
        System.out.println(solution.getPerms(str));
        System.out.println(solution.getResultSize());
    }
}

class Solution {
    private ArrayList<String> result;

    public ArrayList<String> getPerms(String str) {
        result = new ArrayList<>();
        Map<Character, Integer> map = buildFreqTable(str);
        getPerms(map, "", str.length(), result);
        return result;
    }

    public int getResultSize() {
        return result.size();
    }

    public void getPerms(Map<Character, Integer> map, String prefix,
                                      int remaining, ArrayList<String> result) {
        // Base case
        if (remaining == 0) {
            result.add(prefix);
            return;
        }

        // Recursive case - The first choice we make is whether to use an a, b, or c
        //                  as the first character. After that, we have a subproblem
        //                  to solve: find all permutations of the remaining characters,
        //                   and append those to the already picked prefix
        //  -- We are using a hash map to avoid using the same character again. The
        //     remaining variable tracks where we are in the recursion tree and where
        //     we are in the string.
        for (Character c : map.keySet()) {
            int count = map.get(c);

            if (count > 0) {
                map.put(c, count - 1);
                getPerms(map, prefix + c, remaining - 1, result);
                map.put(c, count);
            }
        }
    }

    private Map<Character, Integer> buildFreqTable(String str) {
        Map<Character, Integer> map = new HashMap<>();

        for (Character c : str.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            }
            else {
                map.put(c, 1);
            }
        }

        return map;
    }


}

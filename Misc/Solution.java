import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    public static void main(String[] args) {
        //String test = "ABABABA";
        String test = "ABC";
        System.out.println(longestRepeatedStr(test));
    }

    public static String longestRepeatedStr(String str) {
        HashSet<String> set = new HashSet<>();
        int len = str.length();

        for (int start = 0; start < len; start++) {
            for (int end = start; end <= len; end++) {
                String curr = str.substring(start, end);
                System.out.println(curr);
                set.add(curr);
            }
        }

        for (String val : set) {
            //System.out.println(val);
        }

        return "";
    }
}


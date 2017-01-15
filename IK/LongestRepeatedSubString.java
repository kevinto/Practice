import java.util.HashSet;

/**
 * Created by kevinto on 1/14/17.
 */
public class LongestRepeatedSubString {
    public static void main(String[] args) {
        String test = "ABABABA";
        //String test = "ABC";
        System.out.println(longestRepeatedStr(test));
    }

    public static String longestRepeatedStr(String str) {
        HashSet<String> set = new HashSet<>();
        int len = str.length();
        int maxLen = 0;
        String maxStr = "";

        for (int start = 0; start < len; start++) {
            for (int end = start + 1; end <= len; end++) {
                String curr = str.substring(start, end);

                if (set.contains(curr) && curr.length() > maxLen) {
                    maxLen = curr.length();
                    maxStr = curr;
                } else {
                    set.add(curr);
                }
            }
        }

        return maxStr;
    }
}

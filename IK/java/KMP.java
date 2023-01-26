/**
 * Created by kevinto on 12/13/16.
 */
public class KMP {
    public static void main(String args[]){

        String str = "abcxabcdabcdabcy";
        String subString = "abcdabcy";
        boolean result = exists(str.toCharArray(), subString.toCharArray());
        System.out.print(result);

    }

    public static boolean KMP(String strText, String strPattern) {
        return exists(strText.toCharArray(), strPattern.toCharArray());
    }

    private static boolean exists(char[] text, char[] pattern) {
        int[] lps = computeLongestPrefixSuffixArray(pattern);
        int i = 0, j = 0;

        while (i < text.length && j < pattern.length) {
            if (text[i] == pattern[j]) {
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        if (j == pattern.length){
            return true;
        } else {
            return false;
        }
    }

    private static int[] computeLongestPrefixSuffixArray(char[] pattern) {
        int[] lps = new int[pattern.length];
        int index = 0;

        for (int i = 1; i < pattern.length;) {
            if (pattern[i] == pattern[index]) {
                lps[i] = index + 1;
                index++;
                i++;
            } else {
                if (index != 0) {
                    index = lps[index - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}

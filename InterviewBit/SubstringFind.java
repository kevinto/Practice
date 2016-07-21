/**
 * Created by kevint on 7/20/2016.
 * Locate a substring within a substring
 */
public class SubstringFind {
    public static void main(String[] args) {
        String needle1 = "ab";
        String haystack1 = "xxab";
        System.out.println(strstr(needle1, haystack1));
    }

    public static int strstr(String needle, String haystack) {
        int needleLen = needle.length();
        int haystackLen = haystack.length();
        if (needle.length() == 0 || haystack.length() == 0) {
            return -1;
        } else if (needleLen > haystackLen) {
            return -1;
        }

        char needleFirst = needle.charAt(0);
        for (int i = 0; i < haystackLen; i++) {
            char curr = haystack.charAt(i);
            if (curr == needleFirst && haystackLen - i >= needleLen
                    && matches(haystack, i, needle)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean matches(String haystack, int hayStart, String needle) {
        int hStart = hayStart;
        int nStart = 0;
        int needleLen = needle.length();
        int haystackLen = haystack.length();

        while (nStart < needleLen && hStart < haystackLen) {
            if (needle.charAt(nStart) != haystack.charAt(hStart)) {
                return false;
            }
            nStart++;
            hStart++;
        }
        return true;
    }
}

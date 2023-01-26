/**
 * Created by kevinto on 2/4/17.
 */
public class LongestNonRepeatingSubstring {
    public static void main(String[] args) {
        String s1 = "eceba";
        System.out.println(longNoRepeat(s1));

        String s2 = "ecebaaab";
        System.out.println(longNoRepeat(s2));
    }

    /*
    */
    public static int longNoRepeat(String str) {
        // TODO: Input Checking

        int[] map = new int[256];
        int back = 0, front = 0;
        int maxLen = 0;
        int strLen = str.length();

        for (; front < strLen; front++) {
            char curr = str.charAt(front);
            map[curr]++;

            while (map[curr] > 1 && back <= front) {
                char backChar = str.charAt(back);
                map[backChar]--;
                back++;
            }

            maxLen = Math.max(maxLen, front - back + 1);
        }

        return maxLen;
    }
}

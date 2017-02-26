import java.util.*;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        int[] map = {0, 1};
        int val = 9;

        while (val > 0) {
            System.out.print((map[val % 2]) + " ");
            val /= 2;
        }
    }

    public static String minWindow(String text, String findText) {
        // TODO: Input checking

        int textLen = text.length();
        int findTextLen = findText.length();
        int[] map = genFreqMap(findText);
        int count = 0;
        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        int strStart = -1, strEnd = -1;

        while (right < textLen) {
            char currRight = text.charAt(right);
            map[currRight]--;
            if (map[currRight] >= 0) {
                count++;
            }
            right++;

            while (count == findTextLen && left < right) {
                System.out.println("s: " + left + ", e: " + right);
                char currLeft = text.charAt(left);
                if (map[currLeft] + 1 == 1) {
                    break;
                }
                left++;
            }

            if (count == findTextLen && right - left < minLen) {
                minLen = right - left;
                strStart = left;
                strEnd = right;
                System.out.println("s: " + strStart + ", e: " + strEnd);
            }
        }

        if (strStart != -1 && strEnd != -1) {
            return text.substring(strStart, strEnd);
        } else {
            return "FoundNothing";
        }
    }

    private static int[] genFreqMap(String str) {
        int[] map = new int[256];
        int len = str.length();

        for (int i = 0; i < len; i++) {
            map[str.charAt(i)]++;
        }

        return map;
    }
}


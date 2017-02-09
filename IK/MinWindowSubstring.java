/**
 * Created by kevinto on 12/13/16.
 */
public class MinWindowSubstring {
    public static void main(String[] args) {
        System.out.println(minWindow("AYZABOBECODXBANC", "ABC"));
    }

    public static String minWindow(String text, String findText) {
        int[] hasFound = new int[256];
        int[] needToFind = new int[256];
        int count = 0;
        int minStart = 0;
        int minEnd = 0;
        int minLen = Integer.MAX_VALUE;
        int textLen = text.length();
        int findTextLen = findText.length();

        // Fill up the requirements map
        for (int i = 0; i < findTextLen; i++) {
            needToFind[findText.charAt(i)]++;
        }

        // Iterate through the whole string
        for (int begin = 0, end = 0; end < textLen; end++) {
            char curr = text.charAt(end);

            // Skip non-required letters
            if (needToFind[curr] == 0) {
                continue;
            }

            hasFound[curr]++;
            if (hasFound[curr] <= needToFind[curr]) {
                // Keep track of the total numbers found so far of the required number.
                count++;
            }

            // We found all the required letters. Time to grow and shrink.
            if (count == findTextLen) {
                char frontCurr = text.charAt(begin);

                // Move the front pointer if we have extra of the front required letter.
                // We can have extra of the front because we are inc the end pointer.
                while (needToFind[frontCurr] == 0
                        || hasFound[frontCurr] > needToFind[frontCurr]) {
                    if (hasFound[frontCurr] > needToFind[frontCurr]) {
                        // Remove the extra found letter
                        hasFound[frontCurr]--;
                    }
                    begin++;
                    frontCurr = text.charAt(begin);
                }

                // We might have narrowed our range down. See if min can be reset.
                int currWinLen = end - begin + 1;
                if (currWinLen < minLen) {
                    minLen = currWinLen;
                    minStart = begin;
                    minEnd = end;
                }
            }


        }

        return text.substring(minStart, minEnd + 1);
    }

    // Implementation during coding exercise
    public String minWindowDuringCodingExercise(String text, String findText) {
        // TODO: Input checking
        if (text == null || findText == null || findText.length() > text.length()) {
            return "";
        }

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
                char currLeft = text.charAt(left);

                // Moved up left to the very last character that makes our window valid.
                if (map[currLeft] + 1 == 1) {
                    break;
                }
                map[currLeft]++;// Why did this cause a bug? Because left will move to the left and map will still contain extras for characters in our findText. Now our Map is wrong, and this causes left to go all the way up to right
                left++;
            }

            if (count == findTextLen && right - left < minLen) {
                minLen = right - left;
                strStart = left;
                strEnd = right;
            }
        }

        if (strStart != -1 && strEnd != -1) {
            return text.substring(strStart, strEnd);
        } else {
            return "";
        }
    }

    private int[] genFreqMap(String str) {
        int[] map = new int[256];
        int len = str.length();

        for (int i = 0; i < len; i++) {
            map[str.charAt(i)]++;
        }

        return map;
    }
}

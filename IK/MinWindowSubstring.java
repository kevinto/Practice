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
}

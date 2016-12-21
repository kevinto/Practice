/**
 * Created by kevinto on 12/20/16.
 */
import java.util.*;
import java.util.LinkedList;

public class LongestSubstringWithKDistinctCharacters {
    public static void main(String[] args) {
//        String s1 = "eceba";
//        System.out.println(longestLengthSubWithHashAndQueue(s1));

        String s2 = "ecebaaab";
        System.out.println(longestLengthSubWithHashAndQueue(s2));
    }

    // My implementation using hash and queue
    private static int longestLengthSubWithHashAndQueue(String str) {
        Queue<Character> queue = new LinkedList();
        HashMap<Character, Integer> map = new HashMap<>();
        int len = str.length();
        int maxLen = 0;
        List<Character> subStr = new ArrayList<>();

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
                map.put(curr, 1);
            }
            queue.add(curr);

            if (queue.size() > maxLen) {
                maxLen = queue.size();
                subStr = new ArrayList<>(queue);
            }
        }

        if (queue.size() == len && map.size() <= 1) {
            // There is only 1 distinct character, just return an empty string.
            System.out.println("");
        } else {
            System.out.println(subStr);
        }

        return maxLen;
    }

    // Finds the maximum substring with exactly k unique chars. This implementation generalizes to k number
    // of unique elements. It narrows down the range to 26 lower case letters, and uses an associative map
    // to keep track of number of uniques. Every time we add a new letter in our window we check the number
    // of uniques by looking at our map. While we have too many uniques we keep incrementing start and
    // removing from the associative map.
    static void kUniques(String str, int k)
    {
        int numUniques = 0; // number of unique characters
        int strLen = str.length();

        // Associative array to store the count of characters
        int[] count = new int[26];

        // Traverse the string, Fills the associative array
        // count[] and count number of unique characters
        for (int i = 0; i < strLen; i++)
        {
            if (count[str.charAt(i) - 'a']==0)
                numUniques++;
            count[str.charAt(i)-'a']++;
        }

        // If there are not enough unique characters, show
        // an error message.
        if (numUniques < k)
        {
            System.out.println("Not enough unique characters");
            return;
        }

        // Otherwise take a window with first element in it.
        // start and end variables.
        int currStart = 0, currEnd = 0;

        // Also initialize values for result longest window
        int maxWindowSize = 1, max_window_start = 0;

        // Initialize associative array count[] with zero
        count = new int[26];

        count[str.charAt(0) - 'a']++;  // put the first character

        // Start from the second character and add
        // characters in window according to above
        // explanation
        for (int i = 1; i < strLen; i++)
        {
            // Add the character 'str[i]' to current window
            count[str.charAt(i) - 'a']++;
            currEnd++;

            // If there are more than k unique characters in
            // current window, remove from left side
            while (!isValid(count, k))
            {
                count[str.charAt(currStart)-'a']--;
                currStart++;
            }

            // Update the max window size if required
            if (currEnd-currStart+1 > maxWindowSize)
            {
                maxWindowSize = currEnd-currStart+1;
                max_window_start = currStart;
            }
        }

        System.out.println("max window size: " + maxWindowSize);
    }

    static boolean isValid(int count[], int k)
    {
        int val = 0;
        for (int i=0; i < 26; i++)
            if (count[i] > 0)
                val++;

        // Return true if k is greater than or equal to val
        return (k >= val);
    }
}

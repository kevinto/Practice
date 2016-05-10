package p345;

/**
 * Created by kevint on 5/9/2016.
 */
public class ReverseVowel {
    public static void main(String[] args) {
        System.out.println(reverseVowels("leetcode"));
    }

    public static String reverseVowels(String s) {
        // Null and length check
        if (s == null || s.length() < 2) {
            return s;
        }

        String vowels = "aeiouAEIOU";
        char[] chars = s.toCharArray();
        int start = 0;
        int end = s.length() - 1;

        while (start < end) {

            // Get index of vowel starting from the left side of array
            while (start < end && vowels.indexOf(chars[start]) == -1) {
                start++;
            }

            // Get index of vowel starting from the right side of array
            while (start < end && vowels.indexOf(chars[end]) == -1) {
                end--;
            }

            // Do the swap
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;

            // Move on to the next character after the current
            start++;
            end--;
        }

        return new String(chars);
    }


}

package ch1ArraysAndStrings;

import java.util.Arrays;
import java.util.HashMap;

/*
 * Problem 1.1
 * Problem 1.1 Statement:
 * Is Unique: Implement an algorithm to determine if a string has all unique characters. 
 *            What if you cannot use additional data structures?
 */
public class CheckPerm {
    public static void main (String[] args) {
        runTests();
    }
    
    public static boolean isPermUsingArrayMap(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        
        int[] letters = new int[128];

        char[] s1_array = s1.toCharArray();
        for (char c : s1_array) {
            letters[c]++;
        }

        // This way works because if a letter in s2 doesnt exist in s1, then
        // the letter map will be 0 for that location and will go negative.
        // if we have the same letter counts for both, and we have more of a
        // certain letter than another, then will will have to go negative somewhere.
        for (int i = 0; i < s2.length(); i++) {
            int c = (int)s2.charAt(i);
            letters[c]--;
            if (letters[c] < 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPermUsingSort(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        return sort(s1).equals(sort(s2));
    }

    public static String sort(String str) {
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    public static boolean isPermUsingHashMap(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char currChar = s1.charAt(i);
            if (!map.containsKey(currChar)) {
                map.put(currChar, 1);
            } else {
                map.put(currChar, map.get(currChar) + 1);
            }
        }

        for (int i = 0; i < s2.length(); i++) {
            char currChar = s2.charAt(i);
            if (!map.containsKey(currChar)) {
                return false;
            } else {
                int currVal = map.get(currChar);
                if (currVal - 1 < 0) {
                    return false;
                }
                map.put(currChar, currVal - 1);
            }
        }

        for (char key : map.keySet()) {
            if (map.get(key) != 0) {
                return false;
            }
        }

        return true;
    }

    public static void runTests() {
        boolean result = true;
        
//        result &= isPermUsingArrayMap("abc", "bac");
//        result &= !isPermUsingArrayMap("abc", "bace");
//        result &= !isPermUsingArrayMap("abc", "bae");
//        result &= !isPermUsingArrayMap("aab", "bae");

//        result &= isPermUsingSort("abc", "bac");
//        result &= !isPermUsingSort("abc", "bace");
//        result &= !isPermUsingSort("abc", "bae");
//        result &= !isPermUsingSort("aab", "bae");

        result &= isPermUsingHashMap("abc", "bac");
        result &= !isPermUsingHashMap("abc", "bace");
        result &= !isPermUsingHashMap("abc", "bae");
        result &= !isPermUsingHashMap("aab", "bae");

        if (result) {
            System.out.println("All tests passed!");
        }
        else {
            System.out.println("One or more tests failed...");
        }
    }
}
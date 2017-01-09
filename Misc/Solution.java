import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    public static void main(String[] args) {
        //System.out.println(minWindow("AYZABOBECODXBANC", "ABC"));

        System.out.println("original: " + "this is a test string");
        System.out.println(minWindow("this is a test string", "tist"));
    }

    public static String minWindow(String text, String match) {
        if (text == null || match == null || text.length() == 0 || match.length() == 0) {
            return "";
        }

        // Build freq map and set
        HashMap<Character, Integer> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        int matchLen = match.length();
        for (int i = 0; i < matchLen; i++) {
            char currChar = match.charAt(i);
            if (map.containsKey(currChar)) {
                map.put(currChar, map.get(currChar) + 1);
            } else {
                map.put(currChar, 1);
            }
            set.add(currChar);
        }

        // move front pointer to first valid char spot
        int front = 0;
        int textLen = text.length();
        while (front < textLen && !map.containsKey(text.charAt(front))) {
            front++;
        }

        if (front < textLen && map.containsKey(text.charAt(front))) {
            char currChar = text.charAt(front);
            if (map.get(currChar) == 1) {
                map.remove(currChar);
            } else {
                map.put(currChar, map.get(currChar) - 1);
            }
        }
        int end = front + 1;

        // load the first window with 2 conditions:
        // map not empty and back ptr is less then the end of string
        while (!map.isEmpty() && end < textLen) {
            char currChar = text.charAt(end);
            if (map.containsKey(currChar)) {
                if (map.get(currChar) == 1) {
                    map.remove(currChar);
                } else {
                    map.put(currChar, map.get(currChar) - 1);
                }

                if (map.isEmpty()) {
                    break;
                }
            } else {
                end++;
            }
        }

        // while front < back and back < text.length
        // move back until it matches front
        // move front to next valid letter
        // record new window
        // see if new window is min
        int minFront = -1;
        int minEnd = -1;
        if (front < end && end < textLen) {
            minFront = front;
            minEnd = end;
        }

        while (front < end && end < textLen) {
            end++;
            while (end < textLen && text.charAt(end) != text.charAt(front)) {
                end++;
            }

            if (end >= textLen) {
                break;
            }

            front++;
            while (front < textLen && front <= end && !set.contains(text.charAt(front))) {
                front++;
            }


            if (front > end) {
                break;
            }

            System.out.println("debug: " + text.substring(front, end + 1));

            if ((end - front) < (minEnd - minFront)) {
                minFront = front;
                minEnd = end;
            }
        }

        // return the substring using the front and back ptr.
        if (minFront != -1 && minEnd != -1) {
            return text.substring(minFront, minEnd + 1);
        } else {
            return "";
        }
    }
}


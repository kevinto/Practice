import java.util.*;

public class Solution {
    public static void main(String[] args) {
        System.out.println("hello, bello: " + differBy1Character("hello", "bello"));
        System.out.println("hello, bzllo: " + differBy1Character("hello", "bzllo"));
    }

    private static boolean differBy1Character(String s1, String s2) {
        int s1Len = s1.length();
        int s2Len = s2.length();
        if (s1Len != s2Len) {
            return false;
        }

        char[] map = new char[256];
        for (int i = 0; i < s1Len; i++) {
            map[s1.charAt(i)]++;
        }

        for (int i = 0; i < s2Len; i++) {
            map[s2.charAt(i)]--;
        }

        int negOne = 0;
        int posOne = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] < 0) {
                negOne++;
            } else if (map[i] > 0) {
                posOne++;
            }
        }

        return (negOne == 1 && posOne == 1);
    }
}


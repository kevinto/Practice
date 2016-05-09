package p345Current;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevint on 5/9/2016.
 */
public class ReverseVowel {
    public static void main(String[] args) {
        System.out.println(reverseVowels("hello"));
    }

    public static String reverseVowels(String s) {
        if (s.length() < 2) {
            return s;
        }

        List<Integer> vPos = new ArrayList<>();
        getVowelPos(vPos, s);

        int vPosSize = vPos.size();
        for (int i = 0; i < vPosSize; i++) {
            if (i >= vPosSize - i - 1) {
                break;
            }

            s = swap(s, vPos.get(i), vPos.get(vPosSize - i - 1));
        }
        return s;
    }

    private static void getVowelPos(List<Integer> vPos, String s) {
        int len = s.length();
        String lowerS = s.toLowerCase();
        for (int i = 0; i < len; i++) {
            char currChar = lowerS.charAt(i);
            if (currChar == 'a' || currChar == 'e' || currChar == 'i'||
                    currChar == 'u' || currChar == 'o') {
                vPos.add(i);
            }
        }
    }

    private static String swap(String s, int p1, int p2) {
        StringBuilder returnValue = new StringBuilder(s);

        char temp = s.charAt(p1);
        returnValue.setCharAt(p1, s.charAt(p2));
        returnValue.setCharAt(p2, temp);

        return returnValue.toString();
    }
}

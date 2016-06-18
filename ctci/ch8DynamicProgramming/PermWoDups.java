package ch8DynamicProgramming;

import java.util.ArrayList;

/**
 * Created by Kevin on 6/18/16.
 */
public class PermWoDups {
    public static void main(String args[]) {
        String str = "ab";
        System.out.println(getPerm("abc"));
    }

    public static ArrayList<String> getPerm(String remainder) {
        int len = remainder.length();
        ArrayList<String> result = new ArrayList<>();

        if (len == 0) {
            result.add("");
            return result;
        }

        for (int i = 0; i < len; i++) {
            String before = remainder.substring(0, i);
            String after = remainder.substring(i + 1, len);
            ArrayList<String> partials = getPerm(before + after);

            for (String s : partials) {
                result.add(remainder.charAt(i) + s);
            }
        }

        return result;
    }
}

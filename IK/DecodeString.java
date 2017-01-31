import java.util.Stack;

/**
 * Created by kevinto on 1/30/17.
 *
 * https://leetcode.com/problems/decode-string/
 */
public class DecodeString {
    public static void main(String[] args) {
        String test1 = "3[a]2[bc]";
        String test2 = "3[a2[c]]";
        String test3 = "2[abc]3[cd]ef";

        System.out.println(test1 + ": " + decodeUsing2Stacks(test1));
        System.out.println(test2 + ": " + decodeUsing2Stacks(test2));
        System.out.println(test3 + ": " + decodeUsing2Stacks(test3));
    }

    // Use a pos array to pass by ref. Each time we hit a closing bracket, we return
    // the word in that recursive tree. The first stack frame will skip over the closing
    // bracket after its done with the recursive call.
    public static String decodeRecursive(String str) {
        // TODO: Input checking

        int[] pos = new int[1];
        return decodeRecursive(str, pos);
    }

    public static String decodeRecursive(String str, int[] pos) {
        String word = "";
        int factor = 0;
        for (; pos[0] < str.length(); pos[0]++) {
            char curr = str.charAt(pos[0]);
            if (curr == '[') {
                pos[0]++;
                String temp = decodeRecursive(str, pos);
                for (int j = 0; j < factor; j++) {
                    word += temp;
                }
                factor = 0;
            } else if (curr == ']') {
                return word;
            } else if (isChar(curr)) {
                word += curr;
            } else if (isNumber(curr)) {
                factor *= 10;
                factor += curr - '0';
            }
        }

        return word;
    }

    public static String decodeUsing2Stacks(String str) {
        // TODO: Input checking.

        Stack<Integer> intStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        int len = str.length();
        int i = 0;

        while (i < len) {
            if (str.charAt(i) == '[') {
                i++;
                continue;
            }

            if (intStack.empty() && strStack.empty() && isChar(str.charAt(i))) {
                sb.append(str.charAt(i++));
                continue;
            }

            while (i < len && isChar(str.charAt(i))) {
                temp.append(str.charAt(i++));
            }

            if (temp.length() != 0) {
                strStack.push(temp.toString());
                temp.setLength(0);
                continue;
            }

            int number = 0;
            while (i < len && isNumber(str.charAt(i))) {
                number *= 10;
                number += str.charAt(i) - '0';
                i++;
            }

            if (number != 0) {
                intStack.push(number);
                continue;
            }

            if (str.charAt(i) == ']') {
                int factor = intStack.pop();
                String poppedStr = strStack.pop();
                String tempStr = "";
                for (int j = 0; j < factor; j++) {
                    tempStr += poppedStr;
                }

                while (!strStack.empty()) {
                    tempStr = strStack.pop() + tempStr;
                }

                if (intStack.empty()) {
                    sb.append(tempStr);
                } else {
                    strStack.push(tempStr);
                }

                i++;
            }
        }

        return sb.toString();
    }

    private static boolean isChar(char curr) {
        return curr >= 'a' && curr <= 'z';
    }

    private static boolean isNumber(char curr) {
        return curr >= '0' && curr <= '9';
    }
}

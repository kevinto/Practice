import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * Created by kevinto on 12/24/16.
 */
public class LongestValidSubstring {
    public static void main (String[] args) {
        String str1 = "()(())";
        System.out.println(maxLenMatchingParen(str1) == 6);
        System.out.println(maxLenMatchingParenWithStringStack(str1) == 6);

        String str2 = "((((())(((()";
        System.out.println(maxLenMatchingParen(str2) == 4);
        System.out.println(maxLenMatchingParenWithStringStack(str2) == 4);
    }

    public static int maxLenMatchingParenWithStringStack(String str) {
        int strLen = str.length();
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < strLen; i++) {
            char curr = str.charAt(i);
            if (curr == '(') {
                stack.push("(");
            } else {
                if (stack.empty()) {
                    continue;
                }

                if (stack.peek().equals("(")) {
                    stack.pop();
                    stack.push("()");
                } else if (Objects.equals(stack.peek(), "()")) {
                    int valCt = 0;
                    while (!stack.empty() && stack.peek().equals("()")) {
                        valCt++;
                        stack.pop();
                    }

                    if (!stack.empty() && stack.peek().equals("(")) {
                        stack.pop();
                        stack.push("()");
                    }

                    while (valCt > 0) {
                        valCt--;
                        stack.push("()");
                    }
                }
            }
        }
        List<String> res = new ArrayList<>(stack);
        int sum = 0;
        int maxSum = 0;
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i).equals("()")) {
                sum++;
                maxSum = Math.max(maxSum, sum);
            } else {
                sum = 0;
            }
        }
        return maxSum * 2;
    }

    public static int maxLenMatchingParen(String str) {
        int maxLen = 0;
        int strLen = str.length();
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // This maintains the end of the invalid sequence.

        for (int i = 0; i < strLen; i++) {
            char curr = str.charAt(i);
            if (curr == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (!stack.empty()) {
                    int currLen = i - stack.peek();
                    maxLen = Math.max(maxLen, currLen);
                } else {
                    // This gets popped off when we have an invalid sequence.
                    // The negative number gets popped off. This index marks the end of an invalid sequence
                    stack.push(i);
                }
            }
        }
        return maxLen;
    }
}

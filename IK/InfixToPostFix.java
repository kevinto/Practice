import java.util.Stack;

/**
 * Created by kevinto on 1/21/17.
 */
public class InfixToPostFix {
    public static void main(String[] args) {
        char[] operators = {'c', '+', '*'};
        String digits = "2222";

        String postFix = infixToPostFix(digits, operators);
        System.out.println("Postfix: " + postFix);

        int result = evalPostfix(postFix);
        System.out.println("result: " + result);
    }

    public static String infixToPostFix(String digits, char[] ops) {
        if (digits == null || ops == null || digits.length() != ops.length + 1) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int dIdx = 0;
        int oIdx = 0;
        int digLen = digits.length();
        Stack<Character> stack = new Stack<>();

        while (dIdx < digLen) {
            sb.append(digits.charAt(dIdx++));

            if (oIdx >= ops.length) {
                continue;
            }

            if (stack.empty()) {
                stack.push(ops[oIdx]);
            } else {
                if (!priorityHigher(ops[oIdx], stack)) {
                    flushStack(stack, sb);
                }
                stack.push(ops[oIdx]);
            }
            oIdx++;
        }
        flushStack(stack, sb);

        return sb.toString();
    }

    private static void flushStack(Stack<Character> stack, StringBuilder sb) {
        while (!stack.empty()) {
            sb.append(stack.pop());
        }
    }

    private static boolean priorityHigher(char curr, Stack<Character> stack) {
        char top = stack.peek();
        if (curr == top) {
            return true;
        } else if (curr == 'c') {
            return true;
        } else if (curr == '*' && top == '+') {
            return true;
        }

        return false;
    }

    public static int evalPostfix(String postFix) {
        if (postFix == null || postFix.length() < 3) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int len = postFix.length();
        for (int i = 0; i < len; i++) {
            char curr = postFix.charAt(i);
            if (isInteger(curr)) {
                stack.push(curr - '0');
            } else if (stack.size() >= 2) {
                int first = stack.pop();
                int second = stack.pop();
                int result = getOperResult(first, curr, second);
                stack.push(result);
            }
        }

        if (stack.size() == 1) {
            return stack.pop();
        } else {
            return 0;
        }
    }

    private static int getOperResult(int first, char oper, int second) {
        int result = 0;

        if (oper == 'c') {
            result = first * 10;
            result += second;
        } else if (oper == '*') {
            result = first * second;
        } else if (oper == '+') {
            result = first + second;
        }

        return result;
    }

    private static boolean isInteger(char curr) {
        if (curr >= '0' && curr <= '9') {
            return true;
        }
        return false;
    }
}

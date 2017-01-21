
public class Solution {
    public static void main(String[] args) {
        char[] operators = {'c', '+', '*'};
        System.out.println(evaluateExpression("2222", operators));
    }

    public static int evaluateExpression(String digits, char[] ops) {
        if (digits.length() != ops.length + 1) {
            // Throw error
            return 0;
        }

        int digit;
        int factor = 1;
        boolean multiply = false;
        int result = 0;
        int pos = 0;

        for (;;pos++) {
            digit = digits.charAt(pos) - '0';
            while (pos < ops.length && ops[pos] == 'c') {
                pos++;
                digit *= 10;
                digit += digits.charAt(pos) - '0';
            }

            if (multiply) {
                multiply = false;
                digit *= factor;
            }

            if (pos >= ops.length) {
                result += digit;
                break;
            }

            if (pos < ops.length && ops[pos] == '+') {
                result += digit;
                continue;
            }

            multiply = true;
            factor = digit;
        }

        return result;
    }
}


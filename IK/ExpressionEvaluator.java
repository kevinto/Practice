/**
 * Created by kevinto on 12/1/16.
 */
public class ExpressionEvaluator {
    public String[] expCreator(String strDigits, int k) {
        char[] operators = new char[strDigits.length() - 1];
        expCreator(strDigits, k, operators, 0);
        return null;
    }

    private void expCreator(String operands, int target, char[] operators, int writeIndex) {
        if (writeIndex >= operands.length() - 1) {
            if (evaluate(operands, operators) == target) {
                System.out.print("Found solution");
            }
            return;
        }

        operators[writeIndex] = 'c'; // Stands for combine
        expCreator(operands, target, operators, writeIndex + 1);
        operators[writeIndex] = '*'; // Stands for combine
        expCreator(operands, target, operators, writeIndex + 1);
        operators[writeIndex] = '+'; // Stands for combine
        expCreator(operands, target, operators, writeIndex + 1);
    }

    private int evaluate(String operands, char[] operators) {
        int result = 0;
        boolean multiply = false;
        int factor = 1;
        int length = operands.length();

        for (int i = 0; ; i++) {
            int digit = operands.charAt(i) - '0';
            while (i < length && operators[i] == 'c') {
                digit = 10 * digit + operands.charAt(++i) - '0';
            }

            if (multiply) {
                digit *= factor;
                multiply = false;
            }

            if (i == length - 1) {
                result += digit;
                break;
            }

            if (operators[i] == '+') {
                result += operands.charAt(i) - '0';
                continue;
            }

            multiply = true;
            factor = digit;
        }

        return result;
    }
}

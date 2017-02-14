/**
 * Created by kevinto on 12/1/16.
 */
public class ExpressionEvaluator {
    public static void main(String[] args) {
//        char[] operators = {'c', '+', '*'};
//        char[] operators = {'+', 'c', '*'};
        char[] operators = {'*', '+', 'c'};
        System.out.println(evaluateExpression("2222", operators));
    }

    public String[] expCreator(String strDigits, int k) {
        char[] operators = new char[strDigits.length() - 1];
        expCreator(strDigits, k, operators, 0);
        return null;
    }

    private void expCreator(String operands, int target, char[] operators, int writeIndex) {
        if (writeIndex >= operands.length() - 1) {
            if (evaluateExpression(operands, operators) == target) {
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

    private static int evaluateExpression(String digits, char[] operators) {
        int result = 0;
        boolean multiplication = false;
        int factor = 0;

        for(int i = 0; ; ++i) {
            int digit = digits.charAt(i) - '0';

            // Take care of concat first
            while(i < operators.length && operators[i] == 'c')
                digit = 10 * digit + digits.charAt(++i) - '0';

            // Second, take care of multiplication.
            // Why don't we have a continue here?
            //  This multiplies the curr num with the previous num.
            //  The pointer can be on a plus. We multiply before
            //  we deal with the plus.
            // Also, this is here to take care of the last operation
            // being a multi. pos is increasing on every call. We need to
            // take care of the last digit before we exit out.
            if (multiplication) {
                digit *= factor;
                multiplication = false;
            }

            // We reached the end, exit the loop. This works because it adds the
            // results after multiplication and combination, and it also takes
            // care of addition implicitly.
            if (i == operators.length) {
                result += digit;
                break;
            }

            // Third, take care of adds. When we reach an add,
            // we know to add all the stuff on the left side.
            if (operators[i] == '+') {
                result += digit;
                continue;
            }

            // Why is this here?
            //  Need to save the left as a factor, so I can
            //  multiply it by the right.
            multiplication = true;
            factor = digit;
        }

        return result;
    }
}

/**
 * Created by kevinto on 12/10/16.
 */

import java.io.*;
import java.util.*;

public class SuperStack {
    private static ArrayList<Integer> stack;

    public static void main(String args[] ) throws Exception {
        Scanner sc = new Scanner(System.in);
        int numInputLines = Integer.parseInt(sc.nextLine());
        initStack();

        for (int i = 0; i < numInputLines; i++) {
            String input = sc.nextLine();
            String action = getAction(input);
            // System.out.println(action);
            System.out.println(performAction(action, input));
        }
    }
    public static String performAction(String action, String input) {
        String result = "";
        if (action.equals("push")) {
            int num = getNum(input, 1);
            result = push(num);
        } else if (action.equals("pop")) {
            result = pop();
        } else if (action.equals("inc")) {
            int numElementsToAddTo = getNum(input, 1);
            int sumToAddToEachNum = getNum(input, 2);
            result = inc(numElementsToAddTo, sumToAddToEachNum);
        }
        return result;
    }

    private static String inc(int numElementsToAddTo, int sumToAddToEachNum) {
        int leftToAddTo = numElementsToAddTo;
        for (int i = 0; i < stack.size() && leftToAddTo > 0; i++, leftToAddTo--) {
            stack.set(i, stack.get(i) + sumToAddToEachNum);
        }

        return Integer.toString(stack.get(stack.size() - 1));
    }

    private static String push(int num) {
        stack.add(num);
        return Integer.toString(num);
    }

    private static String pop() {
        stack.remove(stack.size() - 1);
        if (stack.size() == 0) {
            return "EMPTY";
        }

        return Integer.toString(stack.get(stack.size() - 1));
    }

    private static int getNum(String input, int occurenceOfNum) {
        String[] splitArr = input.split(" ");

        if (occurenceOfNum >= splitArr.length) {
            // Error case
            System.out.println("Error: getNum");
            return 0;
        }

        return Integer.parseInt(splitArr[occurenceOfNum]);
    }


    public static void initStack() {
        stack = new ArrayList<>();
    }

    private static String getAction(String input) {
        StringBuilder sb = new StringBuilder();
        int length = input.length();
        // key point when going through a finite string think about using a forloop first before using a while loop
        for (int i = 0; i < length; i++) {
            char curr = input.charAt(i);
            if (curr == ' ') {
                break;
            } else if (curr < '0' || curr > '9') {
                sb.append(curr);
            }
        }

        return sb.toString();
    }
}

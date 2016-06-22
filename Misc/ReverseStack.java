import java.util.Stack;

/**
 * Created by Kevin on 6/22/16.
 */
public class ReverseStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println("Original:");
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);

        reverse(stack);

        System.out.println("Reversed:");
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    public static void reverse(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            int temp  = stack.pop();
            reverse(stack);
            addToBottom(stack, temp);
        }
    }

    public static void addToBottom(Stack<Integer> stack, int bottomNum) {
        if (stack.isEmpty()) {
            stack.push(bottomNum);
        } else {
            int temp = stack.pop();
            addToBottom(stack, bottomNum);
            stack.push(temp);
        }
    }
}

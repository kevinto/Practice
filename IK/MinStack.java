import java.util.Stack;

/**
 * Created by kevinto on 12/12/16.
 */
public class MinStack {
    public static void main(String[] args) {
        MinStack stack = new MinStack();

        stack.push(5);
        stack.push(4);
        stack.push(4);
        stack.push(2);
        stack.push(6);

        System.out.println("min: " + stack.getMin());
        stack.pop();
        stack.pop();
        System.out.println("min: " + stack.getMin());
    }

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);

        if (minStack.empty() || minStack.peek() >= x) {
            minStack.push(x);
        }
    }

    public void pop() {
        int popped = stack.pop();
        if (!minStack.empty() && minStack.peek() == popped) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

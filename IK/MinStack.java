import java.util.Stack;

/**
 * Created by kevinto on 12/12/16.
 */
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);

        if (minStack.empty()) {
            minStack.push(x);
        } else if (minStack.peek() >= x) {
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

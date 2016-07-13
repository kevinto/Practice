package ch3StacksAndQueues;

import java.util.Stack;

/**
 * Created by Kevin on 7/13/16.
 */
public class SortStack {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(2);
        s.push(3);
        s.push(1);
        s.push(4);

        Stack<Integer> res = sortStack(s);
        System.out.println("top: " + res.peek());
        return;
    }

    public static Stack<Integer> sortStack(Stack<Integer> main) {
        Stack<Integer> temp  = new Stack<>();
        while (!main.isEmpty()) {
            int curr = main.pop();
            while (!temp.isEmpty() && temp.peek() > curr) {
                main.push(temp.pop());
            }
            temp.push(curr);
        }

        while (!temp.isEmpty()) {
            main.push(temp.pop());
        }
        return main;
    }
}

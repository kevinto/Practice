import java.util.Stack;

/**
 * Created by kevinto on 12/19/16.
 */
public class AreaUnderHistogram {
    public static void main(String[] args) {
        int[] nums = {6,2,5,4,5,1,6};
        System.out.println(getMaxArea(nums));
    }

    public static int getMaxArea(int[] hist)
    {
        // Create an empty stack. The stack holds indexes of hist[] array
        // The bars stored in stack are always in increasing order of their
        // heights.
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0; // Initalize max area
        int topOfStack;  // To store top of stack
        int areaWithTop; // To store area with top bar as the smallest bar

        // Run through all bars of given histogram
        int index = 0;
        while (index < hist.length) {
            // If this bar is higher than the bar on top stack, push it to stack
            if (stack.empty() || hist[stack.peek()] <= hist[index]) {
                stack.push(index++);


            } else {
                // this bar is lower than top of stack, then calculate area of rectangle
                // with stack top as the smallest (or minimum height) bar. 'index' is
                // 'right index' for the top and element before top in stack is 'left index'
                topOfStack = stack.pop();  // store the top index and pop

                // Calculate the area with hist[topOfStack] stack as smallest bar. If the stack is empty,
                // that means that the current top of the stack is the smallest within the range,
                // so it is okay to just multiply it be the index.
                areaWithTop = hist[topOfStack] * (stack.empty() ? index : index - stack.peek() - 1);

                maxArea = Math.max(maxArea, areaWithTop);
            }
        }

        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        while (stack.empty() == false) {
            topOfStack = stack.pop();
            areaWithTop = hist[topOfStack] * (stack.empty() ? index : index - stack.peek() - 1);

            maxArea = Math.max(maxArea, areaWithTop);
        }

        return maxArea;
    }
}

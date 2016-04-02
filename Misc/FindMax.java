/*
Link: http://www.cs.utah.edu/~germain/PPS/Topics/recursion.html
Problem: Find the maximum value in a list of numbers
*/

public class FindMax {
    public static void main(String[] args) {
        int[] testValues1 = { 1, 2, 3 };
        System.out.println(FindMax(testValues1, 0, 2));
        
        int[] testValues2 = { 9, 2, 3 };
        System.out.println(FindMax(testValues2, 0, 2));
    }
    
    public static int FindMax(int[] values, int start, int end) {
        // Base case
        if (start == end) {
            return values[end];
        }
        
        // Recursive case: Keep cutting down the array until we reach
        // the base case. Compare the return value with the start values
        // on each level of the stack.
        int currentMax = FindMax(values, start + 1, end);
        if (currentMax >= values[start]) {
            return currentMax;       
        }
        else {
            return values[start];
        }
    }
}
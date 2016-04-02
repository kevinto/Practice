/*
Link: http://www.cs.utah.edu/~germain/PPS/Topics/recursion.html
Problem: What is a recursive solution to summing up a list of numbers?
*/

public class FindSum {
    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 4};
        System.out.println(findSum(test1, 0, 3));
    }
    
    public static int findSum(int[] values, int start, int end) {
        if (start == end) {
            return values[end];
        }
        
        int firstNum = findSum(values, start + 1, end);
        return firstNum + values[start];    
    }
}
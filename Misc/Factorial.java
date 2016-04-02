/*
Link: http://www.cs.utah.edu/~germain/PPS/Topics/recursion.html
Problem: What is a recursive solution to a factorial?

Sample: 5! = 5 * 4 * 3 * 2 * 1 = 120
*/
public class Factorial {
    public static void main(String[] args) {
        int test1 = 5;
        System.out.println(factorial(5));
    }
    
    public static int factorial(int value) {
        if (value == 0 || value == 1) {
            return 1;
        }
        
        return value * factorial(value - 1);
    }
}
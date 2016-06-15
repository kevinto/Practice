package ch8DynamicProgramming;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Arrays;

/**
 * Created by kevint on 4/30/2016.
 *
 * Problem: A child is running up a staircase with n steps and can hop either
 *          1 step, 2 steps, or 3 steps at a time. Implement a method to count
 *          how many possible ways the child can run up the stairs.
 *
 *  Hint 1: Approach this problem from the top down. What is the very last hop
 *          the child made?
 *
 *  Hint 2: If we knew the number of paths to each of the steps before step 100,
 *          could we compute the number of steps to 100?
 *
 *  Hint 3: We can compute the number of steps to 100 byt the number of steps to
 *          99, 98, and 97. This corresponds to the child hopping 1, 2, or 3 steps
 *          at the end. Do we add those or multiply them? That is: Is it:
 *          f(100) = f(99) + f(98) + f(97)
 *          or
 *          f(100) = f(99) * f(98) * f(97)
 *
 *  Hint 4: We multiply the values when it's "we do this then this." We add them
 *          when it's "we do this or this."
 *
 *  Hint 5: What is the runtime of this method. Think carefully. Can you optimize?
 *
 *  Hint 6: Try memoization as a way to optimize the recursive calls
 */
public class TripleStepProblem {
    public static void main(String[] args) {
        System.out.println("Tests for brute force with base case at 0:");
        System.out.println(findStepsBrute(0) == 1);
        System.out.println(findStepsBrute(1) == 1);
        System.out.println(findStepsBrute(2) == 2);
        System.out.println(findStepsBrute(3) == 4);
        System.out.println(findStepsBrute(4) == 7);
        System.out.println(findStepsBrute(5) == 13);
        System.out.println(findStepsBrute(6) == 24);
        System.out.println(findStepsBrute(7) == 44);
        System.out.println(findStepsBrute(8) == 81);
        System.out.println(findStepsBrute(9) == 149);

        System.out.println("Tests for brute force with tri base cases:");
        System.out.println(findStepsBruteWithMoreBaseCases(0) == 0);
        System.out.println(findStepsBruteWithMoreBaseCases(1) == 1);
        System.out.println(findStepsBruteWithMoreBaseCases(2) == 2);
        System.out.println(findStepsBruteWithMoreBaseCases(3) == 4);
        System.out.println(findStepsBruteWithMoreBaseCases(4) == 7);
        System.out.println(findStepsBruteWithMoreBaseCases(5) == 13);
        System.out.println(findStepsBruteWithMoreBaseCases(6) == 24);
        System.out.println(findStepsBruteWithMoreBaseCases(7) == 44);
        System.out.println(findStepsBruteWithMoreBaseCases(8) == 81);
        System.out.println(findStepsBruteWithMoreBaseCases(9) == 149);

        System.out.println("Tests for memoization:");
        System.out.println(findStepsDp(0) == 1);
        System.out.println(findStepsDp(1) == 1);
        System.out.println(findStepsDp(2) == 2);
        System.out.println(findStepsDp(3) == 4);
        System.out.println(findStepsDp(4) == 7);
        System.out.println(findStepsDp(5) == 13);
        System.out.println(findStepsDp(6) == 24);
        System.out.println(findStepsDp(7) == 44);
        System.out.println(findStepsDp(8) == 81);
        System.out.println(findStepsDp(9) == 149);
    }

    // Assumes 0 steps mean 1 combos
    public static int findStepsBrute(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else {
            return findStepsBrute(n - 1) + findStepsBrute(n - 2) + findStepsBrute(n - 3);
        }
    }
    // Assumes 0 steps mean 0 combos
    public static int findStepsBruteWithMoreBaseCases(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1){
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (n == 3) {
            return 4;
        } else {
            return findStepsBrute(n - 1) + findStepsBrute(n - 2) + findStepsBrute(n - 3);
        }
    }

    public static int findStepsDp(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return findStepsDpHelper(n, memo);
    }

    public static int findStepsDpHelper(int n, int[] memo) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (memo[n] > -1) {
            return memo[n];
        } else {
            memo[n] = findStepsDpHelper(n - 1, memo) + findStepsDpHelper(n - 2, memo)
                    + findStepsDpHelper(n - 3, memo);
            return memo[n];
        }
    }
}

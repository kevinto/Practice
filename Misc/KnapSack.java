import java.util.HashSet;
import static java.lang.System.*;

/**
 * Created by Kevin on 7/6/16.
 *
 * Solves the knapsack problem. This implementation assumes that we do not keep duplicate items
 */
public class KnapSack {
    private static int memo[];
    private int numItems;
    private int capacity;
    private int[] val;
    private int[] weights;
    private HashSet<Integer> possibleItems;

    public static void main(String[] args) {
        int numItems = 3;
//        int capacity = 20;
//        memo = new int[capacity + 1];
//        int[] val = { 3, 4, 3 };
//        int[] weights = { 6, 7, 9 };
//
//        int result = new KnapSack(numItems, val, weights, capacity).findMaxVal();
//        out.println("Max value we can have in the knapsack: " + result);

        int capacity = 50;
        memo = new int[capacity + 1];
        int[] val2 = { 60, 100, 120 };
        int[] weights2 = { 10, 20, 30 };

//        int result = new KnapSack(numItems, val2, weights2, capacity).findMaxVal();
//        out.println("Max value we can have in the knapsack: " + result);
        int optimal2 = knapSackBacktrack(capacity, weights2, val2, val2.length);
        out.println("Max value using backtracking: " + optimal2);
    }

    private static int knapSackBacktrack(int W, int wt[], int val[], int n)
    {
        // Base Case
        if (n == 0 || W == 0)
            return 0;

        // If weight of the nth item is more than Knapsack capacity W, then
        // this item cannot be included in the optimal solution
        if (wt[n-1] > W)
            return knapSackBacktrack(W, wt, val, n-1);

            // Return the maximum of two cases:
            // (1) nth item included
            // (2) not included
        else return Math.max(val[n-1] + knapSackBacktrack(W-wt[n-1], wt, val, n-1),
                knapSackBacktrack(W, wt, val, n-1)
        );
    }

    private KnapSack(int numItems, int[] val, int[] weights, int capacity) {
        this.numItems = numItems;
        this.val = val;
        this.weights = weights;
        this.capacity = capacity;
        this.possibleItems = new HashSet<>(numItems);
    }

    private int findMaxVal() {
        if (numItems == 0) {
            return 0;
        }

        initMemo();
        initPossibleItems();

        return findMaxVal(capacity, 0);
    }

    private int findMaxVal(int currWeight, int currVal) {
        if (currWeight < 0) return Integer.MIN_VALUE;
        if (memo[currWeight] != -1) return memo[currWeight];

        int result = currVal;
        HashSet<Integer> tracker = new HashSet<>(possibleItems);
        for (int item : tracker) {
            possibleItems.remove(item);
            result = Math.max(findMaxVal(currWeight - weights[item], currVal + val[item]), result);
            possibleItems.add(item);
        }

        memo[currWeight] = result;
        return result;
    }

    private void initMemo() {
        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1;
        }
    }

    private void initPossibleItems() {
        for (int i = 0; i < this.numItems; i++) {
            this.possibleItems.add(i);
        }
    }
}

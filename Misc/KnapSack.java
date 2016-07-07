import static java.lang.System.*;

/**
 * Created by Kevin on 7/6/16.
 *
 * Solves the knapsack problem
 */
public class KnapSack {
    private static int memo[];
    private int numItems;
    private int capacity;
    private int[] val;
    private int[] weights;

    public static void main(String[] args) {
        int numItems = 3;
        int capacity = 20;
        memo = new int[capacity + 1];
        int[] val = { 3, 4, 3 };
        int[] weights = { 6, 7, 9 };

        int result = new KnapSack(numItems, val, weights, capacity).findMaxVal();
        out.println("Max value we can have in the knapsack: " + result);
    }

    private KnapSack(int numItems, int[] val, int[] weights, int capacity) {
        this.numItems = numItems;
        this.val = val;
        this.weights = weights;
        this.capacity = capacity;
    }

    private int findMaxVal() {
        if (numItems == 0) {
            return 0;
        }

        initMemo();

        return findMaxVal(capacity, 0);
    }

    private int findMaxVal(int currWeight, int currVal) {
        if (currWeight < 0) return Integer.MIN_VALUE;
        if (memo[currWeight] != -1) return memo[currWeight];

        int result = currVal;
        for (int i = 0; i < numItems; i++) {
            result = Math.max(findMaxVal(currWeight - weights[i], currVal + val[i]), result);
        }

        memo[currWeight] = result;
        return result;
    }

    private void initMemo() {
        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1;
        }
    }
}

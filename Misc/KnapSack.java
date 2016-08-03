import java.util.HashMap;
import java.util.HashSet;
import static java.lang.System.*;

/**
 * Created by Kevin on 7/6/16.
 *
 * Solves the knapsack problem. This implementation assumes that we do not keep duplicate items
 * http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
 * https://www.youtube.com/watch?v=149WSzQ4E1g
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/Knapsack01.java#L73
 *
 */
public class KnapSack {
    private static int memo[];
    private int numItems;
    private int capacity;
    private int[] val;
    private int[] weights;
    private HashSet<Integer> possibleItems;
    private static HashMap<CapAndNumItem, Integer> memoMap;

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
        int optimal2 = knapSackBacktrack(val2, weights2, capacity, val2.length - 1);
        out.println("Max value using backtracking: " + optimal2);

        // hashmap memo test:
        int capacity3 = 8;
        memoMap = new HashMap<>();
        int[] val3 = { 2, 4, 6, 9 };
        int[] weights3 = { 2, 2, 4, 5 };
        int optimal3 = new KnapSack().knapSackBacktrackWithMemo(val3, weights3, capacity3, val3.length - 1);
        out.println("Max value using backtracking and hashmap memo: " + optimal3);
    }

    private static int knapSackBacktrack(int[] v, int[] w, int cap, int index) {
        if (index < 0 || cap < 0) {
            return 0;
        }

        if (w[index] > cap) {
            return knapSackBacktrack(v, w, cap, index - 1);
        }

        return Math.max(v[index] + knapSackBacktrack(v, w, cap - w[index], index - 1),
                knapSackBacktrack(v, w, cap, index - 1));
    }

    // For the map, why cant i use just the cap for memo?
    //  - For example, the senario where you recurse down and dont pick any item. As you
    //    go back the tree you pick 1 item. This compares scenarios where you pick 1 item only.
    //    If i used cap as the key, then i would not recur down.
    private int knapSackBacktrackWithMemo(int[] v, int[] w, int cap, int index) {
        if (memoMap.containsKey(cap)) {
            return memoMap.get(cap);
        }
        else if (index < 0 || cap < 0) {
            return 0;
        }

        int maxVal = 0;
        if (w[index] > cap) {
            maxVal = knapSackBacktrackWithMemo(v, w, cap, index - 1);
            memoMap.put(new CapAndNumItem(cap, v.length - index), maxVal);
        }

        maxVal = Math.max(v[index] + knapSackBacktrackWithMemo(v, w, cap - w[index], index - 1),
                knapSackBacktrackWithMemo(v, w, cap, index - 1));
        memoMap.put(new CapAndNumItem(cap, v.length - index), maxVal);
        return maxVal;
    }

    class CapAndNumItem {
        int cap;
        int numItemLeft;
        CapAndNumItem(int c, int n) {
            cap = c;
            numItemLeft = n;
        }

        // The bad thing about this implementation approach is that
        // you cannot change this object once you put it in the hashmap.
        // This is because the key changes if the object changes.
        @Override
        public int hashCode() {
            int result = this.cap;
            result = 31 * result + numItemLeft;
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CapAndNumItem c = (CapAndNumItem)o;

            if (cap != c.cap) return false;
            return numItemLeft == c.numItemLeft;
        }
    }

    private KnapSack() {
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

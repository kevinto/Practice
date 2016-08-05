/**
 * Created by kevint on 8/4/2016.
 *
 * Problem Statement:
 * Say you have an array for which the ith element
 * is the price of a given stock on day i.
 * .
 * If you were only permitted to complete at most one transaction
 * (ie, buy one and sell one share of the stock), design an algorithm
 * to find the maximum profit.
 */
public class p121 {
    public static void main(String[] args) {
        int[] test1 = { 7, 1, 5, 3, 6, 4 };
        System.out.println(maxProfit(test1));

        int[] test2 = { 7, 6, 4, 3, 1 };
        System.out.println(maxProfit(test2));

        int[] test3 = { 5, 1, 1, 100 };
        System.out.println(maxProfit(test3));
    }

    // Why does this approach work?
    //      It boils down to this equation:
    //      maxCur = Math.max(0, maxCur - prices[i-1] + prices[i])
    //
    //      If we go negative on the right side, then we are resetting
    //      our maxCur to 0. This means that selling at the current
    //      point won't give you any profit.
    //      If we pick the other side, then it means our profit was
    //      positive at this current point.
    //      -- maxCur above symbolizes the max profit found by selling
    //         at (i - 1)
    //      -- we do this: (maxCur - prices[i-1]) because we want to
    //         find the profit to add to our current sell price at i.
    //         We are essentially "unselling" prices[i-1], so we can
    //         sell at prices[i].
    //      -- This is related to the inclusion-exclusion principle because
    //         if we only add maxCur + prices[i], then we are double counting
    //         the profits when selling at prices[i-1]. We do not want to sell
    //         at prices[i-1] anymore that is why we are removing it from maxCurr


    public static int maxProfit(int[] prices) {
        int maxCur = 0;
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur + prices[i] - prices[i - 1]);
            max = Math.max(max, maxCur);
        }

        return max;
    }
}

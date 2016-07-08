import java.util.Arrays;

/**
 * Created by Kevin on 7/8/16.
 */
public class AwardBudgetCuts {
    public static void main(String[] args) {
        int[] testGrants1 = { 40, 50 };
        int testBudget1 = 88;
        System.out.println("Brute: " + Arrays.toString(testGrants1) + ", budget: " + testBudget1 + " --> " + "Cap: " + new AwardBudgetCuts().getCapBruteForce(testBudget1, testGrants1));

        int[] testGrants2 = { 12, 1, 3, 5 };
        int testBudget2 = 100;
        System.out.println("Brute: " + Arrays.toString(testGrants2) + ", budget: " + testBudget2 + " --> " + "Cap: " + new AwardBudgetCuts().getCapBruteForce(testBudget2, testGrants2));

        int[] testGrants3 = { 40, 50 };
        int testBudget3 = 88;
        System.out.println("Optimal: " + Arrays.toString(testGrants3) + ", budget: " + testBudget3 + " --> " + "Cap: " + new AwardBudgetCuts().getCapOptimal(testBudget3, testGrants3));

        int[] testGrants4 = { 12, 1, 3, 5 };
        int testBudget4 = 100;
        System.out.println("Optimal: " + Arrays.toString(testGrants4) + ", budget: " + testBudget4 + " --> " + "Cap: " + new AwardBudgetCuts().getCapOptimal(testBudget4, testGrants4));
    }

    public int getCapOptimal(int budget, int[] grants) {
        if (grants == null || grants.length == 0) return 0;

        // We are sorting in order to do binary search and to
        // create the partial sum array correctly.
        Arrays.sort(grants);

        // Create the partial sum array
        int currSum = 0;
        int[] partialSums = new int[grants.length];
        for (int i = 0; i < grants.length; i++) {
            currSum += grants[i];
            partialSums[i] = currSum;
        }

        // The last sum in the partial sum array is the sum of
        // entire grants array. If it is already less then the
        // budget than, we are just going to return the most expensive
        // grant as the cap.
        if (partialSums[partialSums.length - 1] <= budget) {
            return grants[grants.length - 1];
        }

        // Find the lowest cap index. The lowest cap index represents the
        // min place in the partial sum array we can be at in order to
        // fall just under our budget. In our original brute force, we
        // subtract 1 until we get under the budget. This optimal method
        // tries the most expensive grant to the least. It makes the max
        // cap to the current grant its on and tries it to see if it falls
        // under budget.
        int start = 0;
        int end = grants.length - 1;
        int lowestCapIdx = 0;
        while (start < end) {
            lowestCapIdx = (int)Math.floor(((end + start) / 2));
            if (lowestCapIdx > 0) {
                if (cappedSum(lowestCapIdx, partialSums, grants) > budget) {
                    if (cappedSum(lowestCapIdx - 1, partialSums, grants) <= budget) {
                        break;
                    } else {
                        end = lowestCapIdx - 1;
                    }
                } else {
                    start = lowestCapIdx + 1;
                }

            }
        }

        int cap = (budget - partialSums[lowestCapIdx - 1]) / (grants.length - lowestCapIdx);
        return cap;
    }

    public int cappedSum(int i, int[] partialSums, int[] grants) {
        if (i - 1 < 0) {
            return (grants[i] * (grants.length - i));
        }
        return partialSums[i - 1] + (grants[i] * (grants.length - i));
    }

    public int getCapBruteForce(int budget, int[] grants) {
        if (grants == null || grants.length == 0) return 0;

        Arrays.sort(grants);

        int sum = getSum(grants);
        int end = grants.length - 1;
        while (sum > budget) {
            int currMax = grants[end];
            for (int i = grants.length - 1; i >= 0; i--) {
                if (grants[i] == currMax) {
                    sum--;
                    grants[i]--;
                } else {
                    break;
                }
            }
        }

        return grants[end];
    }

    public int getSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        return sum;
    }
}

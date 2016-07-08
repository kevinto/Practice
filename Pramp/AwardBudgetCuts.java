import java.util.Arrays;

/**
 * Created by Kevin on 7/8/16.
 */
public class AwardBudgetCuts {
    public static void main(String[] args) {
        int[] testGrants1 = { 3, 2, 1 };
        int testBudget1 = 3;
        System.out.println(Arrays.toString(testGrants1) + " --> " + "Cap: " + new AwardBudgetCuts().getCapBruteForce(testBudget1, testGrants1));

        int[] testGrants2 = { 12, 1, 3, 5 };
        int testBudget2 = 100;
        System.out.println(Arrays.toString(testGrants2) + " --> " + "Cap: " + new AwardBudgetCuts().getCapBruteForce(testBudget2, testGrants2));
    }

    public int getCapBruteForce(int budget, int[] grants) {
        if (grants == null || grants.length == 0) return -1;

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

/**
 * Created by kevinto on 12/14/16.
 */
public class MatrixChainMultiplication {
    public static void main(String[] args) {
        int[] matrixInfo = {40, 20, 30, 10, 30};
        System.out.println(getMin(matrixInfo, 1,matrixInfo.length - 1));
        System.out.println(MatrixChainOrder(matrixInfo, 1, matrixInfo.length - 1));
    }

    static int MatrixChainOrder(int p[], int i, int j)
    {
        if (i == j)
            return 0;

        int min = Integer.MAX_VALUE;

        // place parenthesis at different places between first
        // and last matrix, recursively calculate count of
        // multiplications for each parenthesis placement and
        // return the minimum count
        for (int k=i; k<j; k++)
        {
            int count = MatrixChainOrder(p, i, k) +
                    MatrixChainOrder(p, k+1, j) +
                    p[i-1]*p[k]*p[j];

            if (count < min)
                min = count;
        }

        // Return minimum count
        return min;
    }

    public static int getMin(int[] nums, int i, int j) {
        if (i == j) {
            return 0;
        }

        int min = Integer.MAX_VALUE;

        // k is the right half of the parens. j represents the cols. k represents the commonalities.
        for (int k = i; k < j; k++) {
            int count1 = getMin(nums, i, k);
            int count2 = getMin(nums, k + 1, j);
            int count3 = nums[i - 1] * nums[k] * nums[j]; // represents the work of multiplying both the smaller halfs together
            int count = count1 + count2 + count3;

            if (count < min) {
                min = count;
            }
        }

        return min;
    }

    public static int getMin(int[] nums, int pos) {
        if (pos >= nums.length - 1) {
            return 0;
        }

        int choose;
        if (pos == nums.length - 3) {
            choose = (nums[pos - 1] * nums[pos] * nums[pos + 1]) + (nums[pos - 1] * nums[pos + 2] * nums[pos + 1]);
        } else {
            choose = (nums[pos - 1] * nums[pos] * nums[pos + 1]) + getMin(nums, pos + 2);
        }

        int notChoose = Integer.MAX_VALUE;
        if (pos + 2 < nums.length) {
            notChoose = getMin(nums, pos + 1) + (nums[pos - 1] * nums[pos + 2] * nums[pos]);
        }

        return Math.min(choose, notChoose);
    }
}

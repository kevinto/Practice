/**
 * Created by kevinto on 12/14/16.
 */
public class MatrixChainMultiplication {
    public static void main(String[] args) {
        int[] matrixInfo = {40, 20, 30, 10, 30};
        System.out.println(getMin(matrixInfo, 1,matrixInfo.length - 1));
        System.out.println(getMinDP(matrixInfo, matrixInfo.length));
    }

    static int getMin(int p[], int startIdx, int endIdx)
    {
        // When start and end are equal, this means that
        // there is only 1 matrix. If there is only one matrix,
        // then no multiplications will take place.
        if (startIdx == endIdx) {
            return 0;
        }

        // Set to max value because, we need to set it to lowest minimum.
        int min = Integer.MAX_VALUE;

        // place parenthesis at different places between first
        // and last matrix, recursively calculate count of
        // multiplications for each parenthesis placement and
        // return the minimum count. If there are only 2 elements,
        // we hit the base cases in the two recursive calls
        for (int k=startIdx; k<endIdx; k++)
        {
            // Think of k has where our parenthesis is. We calculate
            // both sides separately and add the work together. The third
            // part is to sum the resultant two matrixes together.
            // The product of two matrices is always the
            // (rows of 1st part) * (common part in the middle) * (cols of the 2nd part)
            // The common part in the middle is where the two matrices meet.
            int count = getMin(p, startIdx, k)
                    + getMin(p, k+1, endIdx)
                    + p[startIdx-1]*p[k]*p[endIdx];

            min = Math.min(count, min);
        }

        // Return minimum count
        return min;
    }

    static int getMinDPNonOptimalSpace(int p[]) {
        int[][] dp = new int[p.length][p.length];
        for (int startCol = 1; startCol < dp[0].length; startCol++) {
            int row = 0;
            int currCol = startCol;
            while (currCol < dp[0].length) {
                dp[row][currCol] = Integer.MAX_VALUE;
                row++;
                currCol++;
            }
        }

        print2d(dp);

        for (int startCol = 1; startCol < dp[0].length; startCol++) {
            int row = 1;
            int currCol = startCol;
            while (currCol < dp[0].length) {
                for (int k = row; k < currCol; k++)
                {
                    int count = dp[row][k]
                            + dp[k + 1][startCol]
                            + p[row-1]*p[k]*p[startCol];
                    dp[row][startCol] = Math.min(dp[row][startCol], count);
                }

                row++;
                currCol++;
            }
        }

        print2d(dp);
        return dp[0][dp[0].length - 1];
    }

    private static void print2d(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Matrix Ai has dimension matrices[i-1] x matrices[i] for i = 1..numElements
    static int getMinDP(int matrices[], int numElements)
    {
        /*
        For simplicity of the program, one extra row and one
        extra column are allocated in dp[][].  0th row and 0th
        column of dp[][] are not used. This is because there
        cant be a matrix at start position 0, since the start
        pos is always at 1. The end pos can also never be at 0
        because that would mean the start is either greater or = to end.
         */
        int dp[][] = new int[numElements][numElements];

        int startIdx, endIdx, parenPos, chainLength, currentCost;

        /* dp[startIdx,endIdx] = Minimum number of scalar multiplications needed
        to compute the matrix A[startIdx]A[startIdx+1]...A[endIdx] = A[startIdx..endIdx] where
        dimension of A[startIdx] is matrices[startIdx-1] x matrices[startIdx] */

        // cost is zero when multiplying one matrix.
        // set all diagonals to 0. This is by default 0 already. Commenting out.
//        for (startIdx = 1; startIdx < numElements; startIdx++)
//            dp[startIdx][startIdx] = 0;

        // Min is 2 because you need 2 matrices to get a product. The max chain
        // is always the number of matrices. The num of matrices is always 1
        // less than the number of elements.
        for (chainLength = 2; chainLength < numElements; chainLength++)
        {
            // We are given a chain length. Now we go through all intervals
            // with that chain length.
            for (startIdx = 1; startIdx < numElements - chainLength + 1; startIdx++)
            {
                // minus 1 to keep the length that matches chain length
                endIdx = startIdx + chainLength - 1;
                System.out.println("start: " + startIdx + " end: " + endIdx);

                // We dont want our end index to go outside of bounds
                if(endIdx == numElements) {
                    continue;
                }

                dp[startIdx][endIdx] = Integer.MAX_VALUE;

                // We only want the parens at the middle of the product of two matices
                for (parenPos = startIdx; parenPos <= endIdx - 1; parenPos++)
                {

                    System.out.println("dp[" + startIdx + "][" + parenPos + "] + dp[" + (parenPos + 1) + "][" + endIdx + "]");
                    // currentCost = cost/scalar multiplications
                    currentCost = dp[startIdx][parenPos] + dp[parenPos + 1][endIdx] + matrices[startIdx - 1] * matrices[parenPos] * matrices[endIdx];
                    if (currentCost < dp[startIdx][endIdx])
                        dp[startIdx][endIdx] = currentCost;
                }
            }
        }

        print2d(dp);
        return dp[1][numElements-1];
    }

    // The solution needs to be calculated continously. You cant choose/notChoose not to multiply.
    // This is complicated when we reach the end matrices.
    public static int getMinWrongWay(int[] nums, int pos) {
        if (pos >= nums.length - 1) {
            return 0;
        }

        int choose;
        if (pos == nums.length - 3) {
            choose = (nums[pos - 1] * nums[pos] * nums[pos + 1]) + (nums[pos - 1] * nums[pos + 2] * nums[pos + 1]);
        } else {
            choose = (nums[pos - 1] * nums[pos] * nums[pos + 1]) + getMinWrongWay(nums, pos + 2);
        }

        int notChoose = Integer.MAX_VALUE;
        if (pos + 2 < nums.length) {
            notChoose = getMinWrongWay(nums, pos + 1) + (nums[pos - 1] * nums[pos + 2] * nums[pos]);
        }

        return Math.min(choose, notChoose);
    }
}

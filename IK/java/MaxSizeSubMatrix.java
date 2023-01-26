import java.util.Arrays;

/**
 * Created by Kevin on 11/7/16.
 * Find the max square submatrix that consists of all 1s.
 * Input matrix can contain 1s and 0s.
 */
public class MaxSizeSubMatrix {
    public static void main(String[] args) {
        int[][] m1 = {
                { 1, 1, 1 },
                { 0, 1, 1 },
                { 1, 1, 1 }
        };

        System.out.println("recursive: " + maxSubmatrix(m1));
        System.out.println("full dp bottom up: " + maxSubmatrixFullBotUpDp(m1));
        System.out.println("full dp bottom up (cleaner version): " + maxSubMatrixFullBottomUpDpCleaner(m1));
        System.out.println("dp bottom up with space savings: " + maxSubmatrixBotUpDpOptimalSpace(m1));

        int[][] m2 = {
                {0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };

        System.out.println("recursive: " + maxSubmatrix(m2));
        System.out.println("full dp bottom up: " + maxSubmatrixFullBotUpDp(m2));
        System.out.println("full dp bottom up (cleaner version): " + maxSubMatrixFullBottomUpDpCleaner(m2));
        System.out.println("dp bottom up with space savings: " + maxSubmatrixBotUpDpOptimalSpace(m2));
    }

    static int maxSubmatrix(int[][] mtx) {
        int max = 0;
        for (int row = 0; row < mtx.length; row++) {
            for (int col = 0; col < mtx[0].length; col++) {
                max = Math.max(max, maxSubmatrix(mtx, row, col));
            }
        }

        return max;
    }

    static int maxSubmatrix(int[][] mtx, int row, int col) {
        if (row == mtx.length || col == mtx[0].length || mtx[row][col] == 0) {
            return 0;
        }

        return Math.min(maxSubmatrix(mtx, row + 1, col),
                Math.min(maxSubmatrix(mtx, row, col + 1),
                        maxSubmatrix(mtx, row + 1, col + 1))) + 1;
    }

    static int maxSubmatrixFullBotUpDp(int[][] mtx) {
        int squareSize = 0;
        int[][] dp = new int[mtx.length][mtx[0].length];

        // Fill out last row with mtx values
        for (int i = 0; i < mtx[0].length; i++){
            dp[mtx.length - 1][i] = mtx[mtx.length - 1][i];
        }

        // Fill out last col with mtx values
        for (int j = 0; j < mtx.length; j++){
            dp[j][mtx[0].length - 1] = mtx[j][mtx[0].length - 1];
        }

        for (int row = mtx.length - 2; row >= 0; row--) {
            for (int col = mtx[0].length - 2; col >= 0; col--) {
                if (mtx[row][col] != 0) {
                    dp[row][col] = min(dp[row + 1][col], dp[row][col + 1], dp[row + 1][col + 1]) + 1;
                    squareSize = Math.max(squareSize, dp[row][col]);
                }
            }
        }

        printMatrix(dp);
        return squareSize;
    }

    public static int maxSubMatrixFullBottomUpDpCleaner(int[][] matrix) {
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];

        int max = 0;
        for(int row = matrix.length - 1; row >= 0; row--) {
            for(int col = matrix[0].length - 1; col >= 0; col--) {
                if (matrix[row][col] != 0) {
                    dp[row][col] = Math.min(dp[row][col + 1],
                            Math.min(dp[row + 1][col + 1], dp[row + 1][col])) + 1;
                    max = Math.max(max, dp[row][col]);
                }
            }
        }

        return max;
    }

    static int maxSubmatrixBotUpDpOptimalSpace(int[][] mtx) {
        int squareSize = 0;
        int[] curr = new int[mtx[0].length + 1];
        int[] prev = new int[mtx[0].length + 1];

        for (int row = mtx.length - 1; row >= 0; row--) {
            for (int col = mtx[0].length - 1; col >= 0; col--) {
                if (mtx[row][col] != 0) {
                    curr[col] = min(prev[col], curr[col + 1], prev[col + 1]) + 1;
                    squareSize = Math.max(squareSize, curr[col]);
                }
            }

            prev = curr;
            curr = new int[mtx[0].length + 1];
        }

//        printMatrix(dp);
        return squareSize;
    }

    static void printMatrix(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }

    static int min(int v1, int v2, int v3) {
        return Math.min(v1, Math.min(v2, v3));
    }
}

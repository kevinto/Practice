/**
 * Created by Kevin on 7/30/16.
 */
public class minCoins {
    public static void main(String[] args) {
        int[] denoms1 = {1, 2, 3};
        int total1 = 5;
        System.out.println(minCoins(denoms1, total1));
    }

    public static int minCoins(int[] denoms, int total) {
        if (denoms == null || denoms.length == 0) {
            return -1;
        }
        if (total == 0) return 0;

        int[][] memo = new int[denoms.length][total + 1];
        initMemo(memo);

        for (int row = 0; row < denoms.length; row++) {
            for (int col = 1; col < total + 1; col++) {
                if (row == 0 && col - denoms[row] < 0) {
                    continue;
                }

                if (col - denoms[row] < 0) {
                    memo[row][col] = memo[row - 1][col];
                    continue;
                }

                if (row == 0) {
                    memo[row][col] = memo[row][col - denoms[row]] + 1;
                } else {
                    memo[row][col] = Math.min(memo[row - 1][col], memo[row][col - denoms[row]] + 1);
                }
            }
        }

        printMatrix(memo);
        return memo[memo.length - 1][memo[0].length - 1];
    }

    private static void initMemo(int[][] memo) {
        for (int row = 0; row < memo.length; row++) {
            for (int col = 1; col < memo[0].length; col++) {
                memo[row][col] = Integer.MAX_VALUE;
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.print(matrix[row][col]);
            }

            System.out.println();
        }
    }
}

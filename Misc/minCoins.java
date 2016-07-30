/**
 * Created by Kevin on 7/30/16.
 *
 */
public class minCoins {
    public static void main(String[] args) {
//        int[] denoms1 = {1, 2, 3};
//        int total1 = 5;
//        minCoinsWith2dMemo(denoms1, total1);
//
//        int[] denoms2 = {1, 5, 6, 8};
//        int total2 = 11;
//        minCoinsWith2dMemo(denoms2, total2);

        int[] denoms3 = {8, 5, 6, 1};
        int total3 = 17;
        minCoinUsingLinearSpace(denoms3, total3);
    }

    public static void minCoinUsingLinearSpace(int[] denoms, int total) {
        int[] minCt = new int[total + 1];
        int[] coinUsed = new int[total + 1];

        initMinCt(minCt);
        initCoinUsed(coinUsed);

        for (int coinIdx = 0; coinIdx < denoms.length; coinIdx++) {
            for (int minCtIdx = denoms[coinIdx]; minCtIdx < minCt.length; minCtIdx++) {
                if (minCt[minCtIdx - denoms[coinIdx]] + 1 < minCt[minCtIdx]) {
                    minCt[minCtIdx] = minCt[minCtIdx - denoms[coinIdx]] + 1;
                    coinUsed[minCtIdx] = coinIdx;
                }
            }
        }
        findPathUsingLinearSpace(coinUsed, denoms);
        System.out.print("linear space min coin for " + total + ": " + minCt[minCt.length - 1]);
    }

    private static void findPathUsingLinearSpace(int[] coinUsed, int[] denoms) {
        int i = coinUsed.length - 1;

        System.out.print("Path: ");
        while (i > 0) {
            int coinValue = denoms[coinUsed[i]];
            System.out.print(coinValue + " ");
            i -= coinValue;
        }
        System.out.println();
    }

    private static void initMinCt(int[] minCt) {
        // Start at 1 because a total of 0 will make 0 coins to make up
        for (int i = 1; i < minCt.length; i++) {
            minCt[i] = Integer.MAX_VALUE - 1;
        }
    }

    private static void initCoinUsed(int[] coinUsed) {
        // Using 1 to indicate no optimal coin found for this total.
        for (int i = 0; i < coinUsed.length; i++) {
            coinUsed[i] = -1;
        }
    }

    public static int minCoinsWith2dMemo(int[] denoms, int total) {
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

        printPathFor2dMemo(memo, denoms);
        System.out.println("min coins for " + total + ": " + memo[memo.length - 1][memo[0].length - 1]);
        return memo[memo.length - 1][memo[0].length - 1];
    }

    private static void printPathFor2dMemo(int[][] memo, int[] denom) {
        int row = memo.length - 1;
        int col = memo[0].length - 1;

        System.out.print("Printing path: ");
        while (row >= 0 && col > 0) {
            if (row == 0) {
                if (memo[row][col - denom[row]] + 1 != memo[row][col]) {
                    System.out.println("Error: No path exists");
                    return;
                } else {
                    System.out.print(denom[row] + " ");
                    col -= denom[row];
                    continue;
                }
            }

            if (memo[row - 1][col] == memo[row][col]) {
                row -= 1;
                continue;
            } else {
                System.out.print(denom[row] + " ");
                col -= denom[row];
            }
        }
        System.out.println("");
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

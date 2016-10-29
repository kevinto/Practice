/**
 * Created by Kevin on 10/29/16.
 */
public class BestPath {
    public static void main(String args[] ) throws Exception {
        int[][] board = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        System.out.println("2d dp arr: " + bestPathBottomUpDp(board));
        System.out.println("2 1d dp arr: " + bestPathBottomUpDpSpaceSave(board));

        int[][] board2 = {
                { 2, 1, 5 },
                { 3, 4, 6 },
                { 1, 12, 3 }
        };

        System.out.println("2d dp arr: " + bestPathBottomUpDp(board2));
        System.out.println("2 1d dp arr: " + bestPathBottomUpDpSpaceSave(board2));
    }

    private static int bestPathBottomUpDp(int[][] board) {
        if (board == null || board.length == 0) {
            return 0;
        }

        int maxRows = board.length;
        int maxCols = board[0].length;
        int[][] dp = new int[maxRows][maxCols];
        dp[maxRows - 1][maxCols - 1] = board[maxRows - 1][maxCols - 1];

        for (int row = maxRows - 1; row >= 0; row--) {
            for (int col = maxCols - 1; col >= 0; col--) {
                if (row == maxRows - 1 && col == maxCols - 1) {
                    continue;
                } else if (row == maxRows - 1) {
                    dp[row][col] = board[row][col] + dp[row][col + 1];
                } else if (col == maxCols - 1) {
                    dp[row][col] = board[row][col] + dp[row + 1][col];
                } else {
                    dp[row][col] = board[row][col] + Math.max(dp[row + 1][col], dp[row][col + 1]);
                }
            }
        }

        return dp[0][0];
    }

    private static int bestPathBottomUpDpSpaceSave(int[][] board) {
        if (board == null || board.length == 0) {
            return 0;
        }

        int maxRows = board.length;
        int maxCols = board[0].length;
        int[] dpCurrent = new int[maxCols];
        int[] dpPrevious = new int[maxCols];
        dpCurrent[maxCols - 1] = board[maxRows - 1][maxCols - 1];

        for (int row = maxRows - 1; row >= 0; row--) {
            for (int col = maxCols - 1; col >= 0; col--) {
                if (row == maxRows - 1 && col == maxCols - 1) {
                    continue;
                } else if (row == maxRows - 1) {
                    dpCurrent[col] = board[row][col] + dpCurrent[col + 1];
                } else if (col == maxCols - 1) {
                    dpCurrent[col] = board[row][col] + dpPrevious[col];
                } else {
                    dpCurrent[col] = board[row][col] + Math.max(dpCurrent[col + 1], dpPrevious[col]);
                }
            }
            dpPrevious = dpCurrent;
            dpCurrent = new int[maxCols];
        }

        return dpPrevious[0];
    }
}



/**
 * Created by kevinto on 11/27/16.
 */
public class NumPaths1sAnd0s {
    public static void main(String[] args) {
        int[][] test1 = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        int numPaths1 = numPathsFullSpaceDp(test1);
        System.out.println("full space dp answer: " + numPaths1);

        int numPaths2 = numPathsMinSpaceDp(test1);
        System.out.println("min space dp answer: " + numPaths2);
    }

    private static int numPathsMinSpaceDp(int[][] board) {
        if (board == null || board.length == 0) {
            return 0;
        }

        int[] curr = new int[board[0].length];
        int[] prev = new int[board[0].length];
        for (int col = board[0].length - 1; col >= 0; col--) {
            if (board[board.length - 1][col] == 0) {
                break;
            }
            prev[col] = 1;
        }

        for (int row = board.length - 2; row >= 0; row--) {
            for (int col = board[0].length - 1; col >= 0; col--) {
                if (col == board[0].length - 1) {
                    curr[col] = board[row][col] == 0 || prev[col] == 0 ? 0 : 1;
                } else if (board[row][col] != 0) {
                    curr[col] = curr[col + 1] + prev[col];
                }
            }
            prev = curr;
            curr = new int[board[0].length];
        }

        return prev[0];
    }

    private static int numPathsFullSpaceDp(int[][] board) {
        if (board == null || board.length == 0) {
            return 0;
        }

        int[][] dp = new int[board.length][board[0].length];

        // Init last row to 1 if possible. Go backwards to
        // see if there are any zeros.
        for (int j = board[0].length - 2; j >= 0; j--) {
            if (board[board.length - 1][j] == 0) {
                break;
            }

            dp[board.length - 1][j] = 1;
        }

        // Init last col to 1 if possible
        for (int i = board.length - 2; i >= 0; i--) {
            if (board[i][board[0].length - 1] == 0) {
                break;
            }

            dp[i][board[0].length - 1] = 1;
        }

        for (int row = board.length - 2; row >= 0; row--) {
            for (int col = board[0].length - 2; col >= 0; col--) {
                if (board[row][col] != 0) {
                    dp[row][col] = dp[row + 1][col] + dp[row][col + 1];
                }
            }
        }

        return dp[0][0];
    }
}

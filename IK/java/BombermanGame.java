/**
 * Created by kevinto on 12/31/16.
 */
public class BombermanGame {
    public static void main(String[] args) {
        char[][] board = {
                {'w', '-', 'e', 'w'},
                {'-', 'w', '-', '-'},
                {'-', 'e', '-', '-'},
                {'w', '-', 'e', '-'}
        };

        System.out.println(bomb(board));
    }

    public static int bomb(char[][] board) {
        int[][] dp = new int[board.length][board[0].length];
        int enemyCt = 0;

        // go down
        for (int col = 0; col < board[0].length; col++) {
            enemyCt = 0;
            for (int row = 0; row < board.length; row++) {
                if (board[row][col] == 'w') {
                    enemyCt = 0;
                } else if (board[row][col] == '-') {
                    dp[row][col] = dp[row][col] + enemyCt;
                } else if (board[row][col] == 'e') {
                    enemyCt++;
                }
            }
        }

        // go up
        for (int col = board[0].length - 1; col >= 0; col--) {
            enemyCt = 0;
            for (int row = board.length - 1; row >= 0; row--) {
                if (board[row][col] == 'w') {
                    enemyCt = 0;
                } else if (board[row][col] == '-') {
                    dp[row][col] = dp[row][col] + enemyCt;
                } else if (board[row][col] == 'e') {
                    enemyCt++;
                }
            }
        }

        // go left
        for (int row = 0; row < board.length; row++) {
            enemyCt = 0;
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == 'w') {
                    enemyCt = 0;
                } else if (board[row][col] == '-') {
                    dp[row][col] = dp[row][col] + enemyCt;
                } else if (board[row][col] == 'e') {
                    enemyCt++;
                }
            }
        }

        // go right
        for (int row = board.length - 1; row >= 0; row--) {
            enemyCt = 0;
            for (int col = board[0].length - 1; col >= 0; col--) {
                if (board[row][col] == 'w') {
                    enemyCt = 0;
                } else if (board[row][col] == '-') {
                    dp[row][col] = dp[row][col] + enemyCt;
                } else if (board[row][col] == 'e') {
                    enemyCt++;
                }
            }
        }

        // get max
        int max = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                max = Math.max(max, dp[row][col]);
            }
        }

        return max;
    }
}

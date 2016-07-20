/**
 * Created by kevint on 7/19/2016.
 */
public class FindIslands {
    public static void main(String[] args) {
        int[][] grid = {
                {1, 0, 0, 1},
                {0, 0, 0 ,1},
                {1, 1, 1 ,0},
                {0, 0, 0 ,1}
        };
        System.out.print(numIslands(grid, grid.length, grid[0].length));
    }

    public static int numIslands(int[][] grid, int rowCount, int colCount) {
        if (grid == null) return 0;

        int islandCount = 0;
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (grid[row][col] == 1) {
                    islandCount++;
                    turn1To0(grid, row, col, rowCount, colCount);
                }
            }
        }
        return islandCount;
    }

    public static void turn1To0(int[][] grid, int row, int col, int totalRow, int totalCol) {
        if (row >= totalRow || col >= totalCol || row < 0 || col < 0) return;
        if (grid[row][col] == 0) return;

        grid[row][col] = 0;

        // Check top
        turn1To0(grid, row - 1, col, totalRow, totalCol);

        // Check bot
        turn1To0(grid, row + 1, col, totalRow, totalCol);

        // Check left
        turn1To0(grid, row, col - 1, totalRow, totalCol);

        // Check right
        turn1To0(grid, row, col + 1, totalRow, totalCol);
    }
}

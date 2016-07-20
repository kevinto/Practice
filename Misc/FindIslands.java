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
        System.out.println("2d approach: " + numIslands(grid, grid.length, grid[0].length));

        int[] grid2 = {
                1, 0, 0, 1,
                0, 0, 0 ,1,
                1, 1, 1 ,0,
                0, 0, 0 ,1
        };
        System.out.println("1d approach: " + numIslands(grid2, 4, 4));
    }

    public static int numIslands(int[] grid, int rowCount, int colCount) {
        if (grid == null) return 0;

        int islandCount = 0;
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                int index = (colCount * row) + col;
                if (grid[index] == 1) {
                    islandCount++;
                    turn1To0(grid, row, col, rowCount, colCount);
                }
            }
        }
        return islandCount;
    }

    public static void turn1To0(int[] grid, int row, int col, int totalRow, int totalCol) {
        int index = (totalCol * row) + col;
        if (row >= totalRow || col >= totalCol || row < 0 || col < 0) return;
        if (index < 0 || index >= grid.length) return;
        if (grid[index] == 0) return;

        grid[index] = 0;

        // Check top
        turn1To0(grid, row - 1, col,totalRow, totalCol);

        // Check bot
        turn1To0(grid, row + 1, col,totalRow, totalCol);

        // Check left
        turn1To0(grid, row, col - 1,totalRow, totalCol);

        // Check right
        turn1To0(grid, row, col + 1,totalRow, totalCol);
    }

    public static int numIslands(int[][] grid, int rowCount, int colCount) {
        if (grid == null) return 0;

        int islandCount = 0;
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                int index = (colCount * row) + col;

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

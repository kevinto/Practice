/**
 * Created by kevint on 8/12/2016.
 *
 * Imagine that you are given a rectangular NxM grid with cells.
 * There is a robot in a starting cell. The robot can pass through
 * some of the cells while others are occupied and it cannot go through
 * them. There is a cell where it must reach. Given the whole grid, the
 * start and end cells, write a program, which determines if the robot can reach its goal.
 */
public class RobotMaze {
    public static void main(String[] args) {
        boolean result = dfsWithInternalStack(new boolean[][]{{true}}, 0, 0, 0, 0);
        result = dfsWithInternalStack(new boolean[][]{{false}}, 0, 0, 0, 0);
//        result = dfsWithInternalStack(TEST_GRID, 0, 0, 6, 5);
//        result = dfsWithInternalStack(TEST_GRID, 0, 0, 6, 3);
//        result = dfsWithInternalStack(TEST_GRID, 0, 0, 1, 0);
//        result = dfsWithInternalStack(TEST_GRID, 6, 3, 2, 2);
        result = dfsWithInternalStack(TEST_GRID, 6, 5, 5, 5);
        System.out.println(result);
    }

    private static final boolean[][] TEST_GRID = new boolean[][]{
            { true, true, true, true, true, true }, // 0
            { true, true, true, true, true, true },
            { true, true, false, true, true, true },
            { true, true, true, true, true, true },
            { true, true, true, true, false, false },
            { true, true, true, true, false, true },
            { true, true, true, true, false, true } // 6
    };

    /*
    Can you convert this to an explicit stack?
        Yes you can. You need to keep track of the visited nodes though
     */
    public static boolean dfsWithInternalStack(boolean[][] grid, int startRow, int startCol, int endRow, int endCol) {
        if (grid == null || grid.length == 0) return false;
        if (startRow < 0 || startCol < 0 || startRow >= grid.length || startCol >= grid[0].length) {
            return false;
        }
        if (!grid[startRow][startCol]) return false;
        if (startRow == endRow && startCol == endCol) {
            return true;
        }

        grid[startRow][startCol] = false;

        return dfsWithInternalStack(grid, startRow - 1, startCol, endRow, endCol) ||
                dfsWithInternalStack(grid, startRow + 1, startCol, endRow, endCol) ||
                dfsWithInternalStack(grid, startRow, startCol - 1, endRow, endCol) ||
                dfsWithInternalStack(grid, startRow, startCol + 1, endRow, endCol);
    }


}

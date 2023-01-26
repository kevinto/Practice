import java.util.*;
import java.util.LinkedList;

/**
 * Created by kevinto on 2/10/17.
 */
public class PacificAtlanticWaterFlow {
    public static void main(String[] args) {
        int[][] board = {
                {1,2,2,3,5},
                {3,2,3,4,4},
                {2,4,5,3,1},
                {6,7,1,4,5},
                {5,1,1,2,4}
        };

        List<int[]> result = pacificAtlantic(board);
        printResult(result);
    }

    public static List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        boolean[][] pacific = getReachability(matrix, 0, 0);
        boolean[][] atlantic = getReachability(matrix, matrix.length - 1, matrix[0].length - 1);

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (pacific[row][col] && atlantic[row][col]) {
                    res.add(new int[] {row, col});
                }
            }
        }

        for (int i = 0; i < pacific.length; i++) {
            System.out.println(Arrays.toString(pacific[i]));
        }
        System.out.println();

        for (int i = 0; i < atlantic.length; i++) {
            System.out.println(Arrays.toString(atlantic[i]));
        }

        return res;
    }

    private static boolean[][] getReachability(int[][] matrix, int originRow, int originCol) {
        boolean[][] reachable = new boolean[matrix.length][matrix[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {originRow, originCol});

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];
            reachable[row][col] = true;

            // Check up
            if (isInBounds(row - 1, col, matrix) && !reachable[row - 1][col]
                    && (matrix[row][col] <= matrix[row - 1][col] || isBorder(row - 1, col, originRow, originCol))) {

                queue.offer(new int[]{row - 1, col});
            }

            // Check right
            if (isInBounds(row, col + 1, matrix) && !reachable[row][col + 1]
                    && (matrix[row][col] <= matrix[row][col + 1] || isBorder(row, col + 1, originRow, originCol))) {

                queue.offer(new int[]{row, col + 1});
            }

            // Check down
            if (isInBounds(row + 1, col, matrix) && !reachable[row + 1][col]
                    && (matrix[row][col] <= matrix[row + 1][col] || isBorder(row + 1, col, originRow, originCol))) {

                queue.offer(new int[]{row + 1, col});
            }

            // Check left
            if (isInBounds(row, col - 1, matrix) && !reachable[row][col - 1]
                    && (matrix[row][col] <= matrix[row][col - 1] || isBorder(row, col - 1, originRow, originCol))) {

                queue.offer(new int[]{row, col - 1});
            }
        }

        return reachable;
    }

    private static boolean isBorder(int row, int col, int originRow, int originCol) {
        return row == originRow || col == originCol;
    }

    private static boolean isInBounds(int row, int col, int[][] matrix) {
        if (row < 0 || col < 0) {
            return false;
        } else if (row >= matrix.length || col >= matrix[0].length) {
            return false;
        }

        return true;
    }

    private static void printResult(List<int[]> list) {
        for (int[] cood : list) {
            System.out.print(Arrays.toString(cood) + " ");
        }
        System.out.println();
    }
}

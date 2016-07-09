import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 7/3/16.
 */
public class SpiralArray {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        List<Integer> res = new SpiralArray().spiralOrder(matrix);
        System.out.println(res);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;

        int currTopRow = 0;
        int currBotRow = matrix.length - 1;
        int currRightcol = matrix[0].length - 1;
        int currLeftcol = 0;

        while (currTopRow <= currBotRow && currLeftcol <= currRightcol) {
            // Print top row from beginning to end
            for (int i = currLeftcol; i <= currRightcol; i++) {
                res.add(matrix[currTopRow][i]);
            }

            // Print right column from the second row till the bottom
            for (int i = currTopRow + 1; i <= currBotRow; i++) {
                res.add(matrix[i][currRightcol]);
            }

            // Print bottom
            for (int i = currRightcol - 1; i >= currLeftcol; i--) {
                res.add(matrix[currBotRow][i]);
            }

            // Print left column
            for (int i = currBotRow - 1; i > currTopRow; i--) {
                res.add(matrix[i][currLeftcol]);
            }

            currTopRow++;
            currLeftcol++;
            currBotRow--;
            currRightcol--;
        }

        return res;
    }
}

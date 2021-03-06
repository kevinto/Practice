import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 7/3/16.
 * Spiral Array Problem
 */
public class ReverseSpirals {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        List<Integer> res = new ReverseSpirals().spiralOrder(matrix);
        System.out.println(res);
    }

    private List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;

        int currTopRow = 0;
        int currBotRow = matrix.length - 1;
        int currLeftCol = 0;
        int currRightCol = matrix[0].length - 1;

        while (currTopRow <= currBotRow && currLeftCol <= currRightCol) {
            // Save the top row starting from the end
            for (int i = currRightCol; i >= currLeftCol; i--) {
                res.add(matrix[currTopRow][i]);
            }

            // Save the left col starting from the top
            for (int i = currTopRow + 1; i <= currBotRow; i++) {
                res.add(matrix[i][currLeftCol]);
            }

            // Save the bot row starting from the left
            for (int i = currLeftCol + 1; i <= currRightCol; i++) {
                res.add(matrix[currBotRow][i]);
            }

            // Save the right col starting from the bot
            for (int i = currBotRow - 1; i > currTopRow; i--) {
                res.add(matrix[i][currRightCol]);
            }

            currTopRow++;
            currBotRow--;
            currLeftCol++;
            currRightCol--;
        }

        return res;
    }
}

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 7/3/16.
 * Spiral Array Problem
 */
public class SpiralArray {
    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        List<Integer> res = new SpiralArray().spiralOrder(matrix1);
        System.out.println(res);

        int[][] matrix2 = {
            {1, 2, 3},
            {5, 6, 7},
            {9, 10, 11},
            {13, 14, 15}
        };
        res = new SpiralArray().spiralOrder(matrix2);
        System.out.println(res);
    }

    private List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        int currTop = 0;
        int currBot = matrix.length - 1;
        int currLeft = 0;
        int currRight = matrix[0].length - 1;

        while(currTop <= currBot && currLeft <= currRight) {
            // Save top
            for (int i = currLeft; i <= currRight; i++) {
                result.add(matrix[currTop][i]);
            }

            // Save right
            for (int i = currTop + 1; i <= currBot; i++) {
                result.add(matrix[i][currRight]);
            }

            // Save bot
            for (int i = currRight - 1; i >= currLeft; i--) {
                result.add(matrix[currBot][i]);
            }

            // Save left
            for (int i = currBot - 1; i > currTop; i--) {
                result.add(matrix[i][currLeft]);
            }

            currTop++;
            currBot--;
            currLeft++;
            currRight--;
        }

        return result;
    }
}

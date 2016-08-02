/**
 * Created by kevint on 8/2/2016.
 */
public class TwoDMatrixMaxSubMatrix {
    public static void main(String[] args) {
        int[][] testMatrix1 = {
                {0, -2, -7, 0},
                {9, 2, -6, 2},
                {-4, 1, -4, 1},
                {-1, 8, 0, -2}
        };
        System.out.println(findMaxBruteForce(testMatrix1));
    }

    public static int findMaxBruteForce(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int maxSum = Integer.MIN_VALUE;
        int tempSum;
        int n = matrix.length;

        for (int startRow = 0; startRow < n; startRow++) {
            for (int startCol = 0; startCol < n; startCol++) {
                for (int endRow = startRow; endRow < n; endRow++) {
                    for (int endCol = startCol; endCol < n; endCol++) {
                        tempSum = findMatrixSum(matrix, startRow, startCol, endRow, endCol);
                        maxSum = Math.max(maxSum, tempSum);
                    }
                }
            }
        }

        return maxSum;
    }

    public static int findMatrixSum(int[][] matrix, int startRow, int startCol, int endRow, int endCol) {
        int sum = 0;
        for (int i = startRow; i <= endRow; i++) {
            for (int k = startCol; k <= endCol; k++) {
                sum += matrix[i][k];
            }
        }

        return sum;
    }
}

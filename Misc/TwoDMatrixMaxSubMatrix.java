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
        System.out.println("brute force:" + findMaxBruteForce(testMatrix1));
        System.out.println("kadane's: " + findMaxOptimal(testMatrix1));
    }

    public static int findMaxOptimal(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int n = matrix.length;
        int max = 0;
        populateSums(matrix);

        for (int startRow = 0; startRow < n; startRow++) {
            for (int startCol = 0; startCol < n; startCol++) {
                for (int endRow = startRow; endRow < n; endRow++) {
                    for (int endCol = startCol; endCol < n; endCol++) {
                        int currMax = matrix[endRow][endCol];
                        if (startRow > 0) currMax -= matrix[startRow - 1][endCol];
                        if (startCol > 0) currMax -= matrix[endRow][startCol - 1];
                        if (startRow > 0 && startCol > 0) currMax += matrix[startRow - 1][startCol - 1];
                        max = Math.max(max, currMax);
                    }
                }
            }
        }

        return max;
    }

    public static void populateSums(int[][] matrix) {
        int n = matrix.length;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int sum = matrix[row][col];
                if (row > 0) matrix[row][col] += matrix[row - 1][col];
                if (col > 0) matrix[row][col] += matrix[row][col - 1];
                if (row > 0 && col > 0) matrix[row][col] -= matrix[row - 1][col - 1];
            }
        }
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

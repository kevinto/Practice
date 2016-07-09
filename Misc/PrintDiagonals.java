/**
 * Created by Kevin on 7/8/16.
 * Print a matrix's diagonals
 */
public class PrintDiagonals {
    public static void main(String[] args) {
        String[][] test1 = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"},
                {"a", "b", "c"}
        };

        printDiagonals(test1);
    }

    private static void printDiagonals(String[][] matrix) {
        if (matrix == null || matrix.length == 0) return;

        for (int i = matrix.length - 1; i >= 0; i--) {
            printDiagonalHelper(i, 0, matrix);
        }

        for (int i = 1; i < matrix[0].length; i++) {
            printDiagonalHelper(0, i, matrix);
        }
    }

    private static void printDiagonalHelper(int startRow, int startCol, String[][] matrix) {
        int currRow = startRow;
        int currCol = startCol;
        while (currRow < matrix.length && currCol < matrix[0].length) {
            System.out.print(matrix[currRow][currCol] + " ");
            currRow++;
            currCol++;
        }
        System.out.println("");
    }
}

import java.util.*;
import java.util.LinkedList;

public class Solution {
    public static void main(String args[] ) throws Exception {
//        System.out.println(findWays(2,2));
        System.out.println(findWays(3,2));
        System.out.println(findWays(3,3));

        int[][] board = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println(subQuadValid(board, 0, 0));

//        int[] arr = {5, 1, 10, 3};
//        partition(arr, 0, 3);
//        System.out.println(Arrays.toString(arr));

        int[] arr1 = {5, 1, 10, 3};
//        partition(arr1, 0, arr1.length - 1);
//        quickSort(arr1, 0, arr1.length - 1);
        System.out.println(Arrays.toString(arr1));
    }





    public static int partition(int[] arr, int start, int end) {
        int pivot = arr[start];
        int i = start + 1;
        int j = end;

        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
                continue;
            }

            while (arr[j] > pivot) {
                j--;
                continue;
            }

            if (i <= j) {
                swap(arr, i++, j--);
            }
        }

        swap(arr, j, start);
        return j;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

//    public static boolean isValid(int[][] board) {
//        for (int row = 0; row < board.length; row++) {
//            for (int col = 0; col < board[0].length; col++) {
//                if (!rowValid(board, row, col) || !colValid(board, row, col) || !subQuadValid(board, row, col)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    public static boolean subQuadValid(int[][] board, int row, int col) {
        int rowMin = 0;
        int rowMax = 0;
        int colMin = 0;
        int colMax = 0;

        // Set row range
        if (row <= 2) {
            rowMin = 0;
            rowMax = 2;
        } else if (row <= 5) {
            rowMin = 3;
            rowMax = 5;
        } else { // row <= 8
            rowMin = 6;
            rowMax = 8;
        }

        // Set col range
        if (col <= 2) {
            colMin = 0;
            colMax = 2;
        } else if (col <= 5) {
            colMin = 3;
            colMax = 5;
        } else { // col <= 8
            colMin = 6;
            colMax = 8;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = rowMin; i <= rowMax; i++) {
            for (int j = colMin; j <= colMax; j++) {
                if (set.contains(board[i][j])) {
                    return false;
                }
                set.add(board[i][j]);
            }
        }

        return true;
    }

    private static void printDP(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void initDP(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
    }

    public static int findWays(int rows, int cols) {
        int[][] visited = new int[rows][cols];
        visited[0][0] = 1;

        return findWays(rows, cols, visited, 1, 0) // down
            + findWays(rows, cols, visited, 0, 1); // right
    }

    private static int findWays(int rows, int cols, int[][] visited, int currRow, int currCol) {
        if (currRow < 0 || currCol < 0 || currRow >= rows || currCol >= cols
                || visited[currRow][currCol] == 1) {
            return 0;
        } else if (currRow == rows - 1 && currCol == cols - 1) {
            return 1;
        }

        visited[currRow][currCol] = 1;
        int count = findWays(rows, cols, visited, currRow - 1, currCol); // Go up
        count += findWays(rows, cols, visited, currRow, currCol + 1); // Go right
        count += findWays(rows, cols, visited, currRow + 1, currCol); // Go down
        count += findWays(rows, cols, visited, currRow, currCol - 1); // Go left
        visited[currRow][currCol] = 0;

        return count;
    }

    public static int numIslands(int[][] matrix) {
        int count = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 1) {
                    count++;
                    visit(matrix, row, col);
                }
            }
        }

        return count;
    }

    private static void visit(int[][] matrix, int row, int col) {
        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length) {
            return;
        } else if (matrix[row][col] == 0 || matrix[row][col] == 2) {
            return;
        }

        matrix[row][col] = 2;
        visit(matrix, row + 1, col); // Move Top
        visit(matrix, row + 1, col + 1); // Move top right
        visit(matrix, row, col + 1); // move right
        visit(matrix, row - 1, col + 1); // move down right
        visit(matrix, row + 1, col); // move down
        visit(matrix, row + 1, col - 1); // move down left
        visit(matrix, row, col - 1); // move left
        visit(matrix, row + 1, col - 1); // move up left
    }
}


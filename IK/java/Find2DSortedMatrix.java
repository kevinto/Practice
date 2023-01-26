/**
 * Created by kevinto on 12/11/16.
 */
public class Find2DSortedMatrix {
    public static void main(String[] args) {
        int[][] test1 = {
                {10, 20, 30, 40},
                {15, 25, 35, 45},
                {27, 29, 37, 48},
                {32, 33, 39, 50},
        };

        System.out.println(search(test1, 29));
        System.out.println(search(test1, 50));
        System.out.println(search(test1, 51));
        System.out.println(search(test1, 34));
    }

    public static boolean search(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            int curr = matrix[row][col];
            if (target > curr) {
                row++;
            } else if (target < curr) {
                col--;
            } else {
                return true;
            }
        }

        return false;
    }
}

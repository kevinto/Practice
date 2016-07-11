import java.util.Arrays;
import java.util.List;

/**
 * Created by Kevin on 7/10/16.
 */
public class RotateArray90 {
    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int[][] matrix2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        RotateArray90 r = new RotateArray90();
        r.rotate(matrix1);
        r.printMatrix(matrix1);

        r.rotate(matrix2);
        r.printMatrix(matrix2);
    }

    public void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println();
    }

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;

        int n = matrix.length;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - first;

            for (int i = first; i < last; i++) {
                int offset = i - first;

                // Save top
                int top = matrix[first][i];

                // left -> top
                matrix[first][i] = matrix[last - offset][first];

                // bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                // top -> right
                matrix[i][last] = top;
            }
        }
    }

//    public void rotate(int[][] matrix) {
//        // Input check
//        if (matrix == null || matrix.length == 0) return;
//
//        int n = matrix.length;
//
//        // Only going to the layers that can be rotated.
//        for (int layer = 0; layer < n / 2; layer++) {
//            // Vars is here b/c the name sounds good
//            int first = layer;
//            int last = n - 1 - layer;
//
//            // We don't want to iterate to the last element b/c
//            // it would be swapped already.
//            for (int i = first; i < last; i++) {
//                // Allows us to look backwards from the end of the array
//                // How does the offset compensate for the next layer?
//                    // offset will always be 0 on the first iteration.
//                    // offset will always be 1 on the second iteration..and so on.
//                int offset = i - first;
//
//                // Save top
//                int top = matrix[first][i];
//
//                // Replace top with left
//                matrix[first][i] = matrix[last - offset][first]; // last - offset -> signifies starting at bottom of left col
//                                                                 // first -> signifies starting first column or the current layer
//
//                // Replace left with bottom
//                matrix[last - offset][first] = matrix[last][last - offset];
//
//                // Replace bottom with right
//                matrix[last][last - offset] = matrix[i][last];
//
//                // Replace right with top
//                matrix[i][last] = top;
//            }
//        }
//    }
}

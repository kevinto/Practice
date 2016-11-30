import java.util.*;
import java.util.LinkedList;

public class Solution {
    public static void main(String args[] ) throws Exception {
        int[][] matrix = {
                {1, 2, 3, 4},
                {8, 9, 10, 11},
                {12, 13, 14, 15},
                {16, 17, 18, 19},
        };
        printSpiralTraversal(matrix);
        System.out.println();

        int[][] matrix1 = {
                {1, 2, 3},
                {8, 9, 10},
                {12, 13, 14},
        };
        printSpiralTraversal(matrix1);
    }

    private static void printSpiralTraversal(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while(top <= bottom && left <= right) {
            // Print top
            for (int tempCol = left; tempCol <= right; tempCol++) {
                System.out.print(matrix[top][tempCol] + " ");
            }

            // Print right
            for (int tempRow = top + 1; tempRow <= bottom; tempRow++) {
                System.out.print(matrix[tempRow][right] + " ");
            }

            // Print bottom
            for (int tempCol = right - 1; tempCol >= left; tempCol--) {
                System.out.print(matrix[bottom][tempCol] + " ");
            }

            // Print left
            for (int tempRow = bottom - 1; tempRow > top; tempRow--) {
                System.out.print(matrix[tempRow][left] + " ");
            }

            top++;
            bottom--;
            left++;
            right--;
        }
    }

    public static void incMid(char[] nums) {
        boolean carry;
        int mid = (nums.length) / 2;
        if (nums.length % 2 == 0) {
            mid--;
        }

        do {
            carry = nums[mid] == '9';
            if (carry) {
                nums[mid] = '0';
                mid--;
            } else {
                nums[mid] = (char)(nums[mid] + 1);
            }
        } while (mid >= 0 && carry);
    }
}


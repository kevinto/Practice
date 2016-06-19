package ch8DynamicProgramming;

import java.util.Arrays;

/**
 * Created by Kevin on 6/19/16.
 */
public class PaintFill {
    public static void main(String[] args) {
        Color[][] testMatrix = new Color[3][3];

        for (Color[] row : testMatrix) {
            Arrays.fill(row, Color.Black);
        }

        testMatrix[0][1] = Color.Yellow;
        testMatrix[2][1] = Color.Yellow;
        testMatrix[1][0] = Color.Yellow;
        testMatrix[1][2] = Color.Yellow;

        fill(testMatrix, 1, 1, Color.White);
        System.out.println(Arrays.toString(testMatrix[0]));
        System.out.println(Arrays.toString(testMatrix[1]));
        System.out.println(Arrays.toString(testMatrix[2]));
    }

    enum Color { Black, White, Red, Yellow, Green }

    // This method does not take care of diagonal fillings. It only compensates for
    // for the element in a "+" shape
    public static void fill(Color[][] screen, int r, int c, Color newColor) {
        if (r < 0 || r >= screen.length || c < 0 || c >= screen[0].length) {
            return;
        }

        if (screen[r][c] != newColor) {
            fill(screen, r, c, screen[r][c], newColor);
        }
    }

    public static void fill(Color[][] screen, int r, int c, Color originalColor, Color newColor) {
        if (r < 0 || r >= screen.length || c < 0 || c >= screen[0].length) {
            return;
        }

        if (screen[r][c] != originalColor) {
            return;
        }

        screen[r][c] = newColor;
        fill(screen, r - 1, c, originalColor, newColor);
        fill(screen, r + 1, c, originalColor, newColor);
        fill(screen, r, c - 1, originalColor, newColor);
        fill(screen, r, c + 1, originalColor, newColor);
    }
}

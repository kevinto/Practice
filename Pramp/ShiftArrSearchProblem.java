import static org.junit.Assert.assertEquals;
/**
 * Created by kevint on 5/2/2016.
 *
 * Problem:
 * 1. Find a given number num in a sorted array arr:
 *      arr = [2, 4, 5, 9, 12, 17]
 *
 * 2. If the sorted array arr is shifted left by an
 *    unknown offset and you don't have a pre-shifted copy of it,
 *    how would you modify your method to find a number in the shifted array?
 *      shiftArr = [9, 12, 17, 2, 4, 5]
 */
public class ShiftArrSearchProblem {
    public static void main(String[] args) {
        int[] sortedArr = { 2, 4, 5, 9, 12, 17 };
        assertEquals(sortedArr[0] , 2);
    }

    public static int binarySearch() {
        return 0;
    }
}

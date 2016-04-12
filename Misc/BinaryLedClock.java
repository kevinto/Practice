/**
 * Created by kevint on 4/11/2016.
 *
 * Problem: The twelve hour clock format is:
 * 0000 : 000000 in binary. Do not worry about AM/PM.
 * Find all the times that have three ones in it only.
 */
public class BinaryLedClock {
    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        int hours = 0b0000;
        int minutes = 0b000000;
        System.out.println(Integer.toBinaryString(hours) + " : " + Integer.toBinaryString(minutes));

        // Find all possible 3 combos of hours
        // Find all possible 3 combos of minutes

        // Poss 1: bit shifting based off different starting configs
        // 0000 : 000111 =0:07
        // 0000 : 001101 =0:13
        // 0000 : 010101 =0:25
        // 0001 : 001001 =1:09
        // 0100 : 010001

        // Poss 2: Start with decimal number. Check if valid. Convert to
        //         binary. Check if 3 ones. Print if there are only 3 ones

        // Poss 3: combine hours and minutes and find all possible numbers
        //         that can be generated for binary numbers. Add 1 and check
        //         validity of number.
    }
}

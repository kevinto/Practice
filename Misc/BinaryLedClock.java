/**
 * Created by kevint on 4/11/2016.
 *
 * Problem: The twelve hour clock format is:
 * 0000 : 000000 in binary. Do not worry about AM/PM.
 * Find all the times that have three ones in it only.
 */
public class BinaryLedClock {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        solve();
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1000000;
        System.out.println("Duration: " + durationMs + "ms");
    }

    private static void solve() {

        for (int hour = 0; hour < 12; hour++) {
            int hourNumOnes = getNumOnes(Integer.toBinaryString(hour));
            if (hourNumOnes > 3) {
                continue;
            }

            for (int minute = 0; minute < 60; minute++) {
                int minNumOnes = getNumOnes(Integer.toBinaryString(minute));
                if (minNumOnes > 3) {
                    continue;
                }

                int hourMinsNumOnes = hourNumOnes + minNumOnes;
                if (hourMinsNumOnes == 3) {
                    System.out.println(hour + ":" + minute + " = " + Integer.toBinaryString(hour) + " : " + Integer.toBinaryString(minute));
                }
            }
        }
    }

    private static int getNumOnes(String value) {
        if (value == null) {
            return 0;
        }

        int onesCount = 0;
        for (int i = 0; i < value.length(); i++) {
            char currChar = value.charAt(i);
            if (currChar == '1') {
                onesCount++;
            }
        }

        return onesCount;
    }
}

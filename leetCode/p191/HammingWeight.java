package p191;

/**
 * Created by Kevin on 4/14/16.
 */
public class HammingWeight {
    public static void main(String[] args) {
        System.out.println(hammingWeight(11));
    }

    public static int hammingWeight(int n) {
        int numOnes = 0;
        String binStr = Integer.toBinaryString(n);
        for(int i = 0; i < binStr.length(); i++) {
            if (binStr.charAt(i) == '1') {
                numOnes++;
            }
        }

        return numOnes;
    }
}

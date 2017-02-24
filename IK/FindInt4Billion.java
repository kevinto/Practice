import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by kevinto on 11/26/16.
 */
public class FindInt4Billion {
    public static void main(String[] args) {
        int[] arr = {1, 10000, 1000000000, 131072, 131073};
        int result = findMissingNumber(arr);
        System.out.println("findMissingNumber: " + result);


//        // Shifts the bits 31 to the left. Inc the number.
//        int bitmap = 1 << 31;
//        System.out.println(bitmap);
//
////        System.out.println(bitmap == (1 << 31));
//        System.out.println(bitmap & (1 << 30)); // Returns 0 because not set.
//
//        System.out.println("new section");
////        int radix = 8;
////        byte[] bitfield = new byte[(0xffffffff)/radix];
//        bitfield[0] = 1;
//        System.out.println(0xffffffff/radix); // 8 F stands for max integer.
//                                              // (max integer) / 8 = how many byte chunks we need.
//        System.out.println(0xffffffff); // represents hex
////        bitfield[1000/radix] |= (1 << (1000%radix));
//        System.out.println("lenth of bitfield: " + bitfield[1/radix]); // represents hex

    }

    public static int findMissingNumber(int[] nums) {
        // There are 2^16 prefixes in int32. Each prefix has 2^16 suffixes.
        // Full up our buckets for the prefixes encountered.
        int prefixLength = (int)Math.pow(2, 16);
        short[] buckets = new short[prefixLength];
        for (int num : nums) {
            int prefix = num >> 16;
            buckets[prefix]++;
        }

        // Find a prefix bucket that is not full.
        // That bucket will have a missing number.
        int selectedBucket = -1;
        for(int bucket : buckets) {
            if (bucket < prefixLength) {
                selectedBucket = bucket;
                break;
            }
        }

        // All buckets are full
        if (selectedBucket == -1) {
            return -1;
        }

        // Buckets now represents all the 2^16 possible suffixes
        // for our chosen prefix. Fill up our buckets with all the suffixes
        // that come with our chosen prefix.
        buckets = new short[prefixLength];
        for (int num : nums) {
            int prefix = num >> 16;
            if (prefix == selectedBucket) {
                short suffix = (short)num;
                buckets[(int)suffix]++;
            }
        }

        // Find the suffix that is missing.
        int suffix = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == 0) {
                suffix = i;
                break;
            }
        }

        int missingNum = (selectedBucket << 16) + suffix;
//        missingNum |= (selectedBucket << 16); // Add the prefix
//        missingNum |= (suffix); // Add the suffix

        return missingNum;
    }

    // Solution to the 1gb limit.
    private static int radix = 8;
//    private static byte[] bitfield = new byte[0xffffffff/radix]; // something wrong with choosing fffff
    private static byte[] bitfield = new byte[Integer.MAX_VALUE / radix];
    void F() throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader("a.txt"));
        while(in.hasNextInt()){
            int n = in.nextInt();

            // Determines what bucket to put the number in. ORs it
            // so we can hold dups
            bitfield[n/radix] |= (1 << (n%radix));
        }

        // Goes through all every byte in the byte[] to find the missing number.
        for(int i = 0; i< bitfield.length; i++){
            for(int j =0; j<radix; j++){
                if( (bitfield[i] & (1<<j)) == 0) System.out.print(i*radix+j);
            }
        }
    }
}

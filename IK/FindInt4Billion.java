import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by kevinto on 11/26/16.
 */
public class FindInt4Billion {
    public static void main(String[] args) {
        int bitmap = 1 << 31;
        System.out.println(bitmap);
//        System.out.println(bitmap == (1 << 31));
        System.out.println(bitmap & (1 << 30)); // Returns 0 because not set.

        System.out.println("new section");
//        int radix = 8;
//        byte[] bitfield = new byte[(0xffffffff)/radix];
        bitfield[0] = 1;
        System.out.println(0xffffffff/radix); // 8 F stands for max integer.
                                              // (max integer) / 8 = how many byte chunks we need.
        System.out.println(0xffffffff); // represents hex
//        bitfield[1000/radix] |= (1 << (1000%radix));
        System.out.println("lenth of bitfield: " + bitfield[1/radix]); // represents hex

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

package DS;

import java.util.BitSet;

/**
 * Created by kevin on 5/23/2016.
 */
public class DS {
    public static void main(String[] args) {
        runBitSet();
    }

    public static void runBitSet() {
        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);

        for (int i = 0; i < 16; i++) {
            if(i % 2 == 0) bits1.set(i);
            if(i % 5 == 0) bits2.set(i);
        }

        System.out.println("Initial pattern in bits1: ");
        System.out.println(bits1); // Only prints the bits that are set
        System.out.println(bits1.get(1));
    }
}

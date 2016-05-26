package DS;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kevin on 5/23/2016.
 */
public class DS {
    public static void main(String[] args) {
        runBitSet();
        runArrayList();
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

    public static void runArrayList() {
        List<String> arrList = new ArrayList<>();
        System.out.println("Initial size of arraylist: " + arrList.size());

        arrList.add("C");
        arrList.add("A");
        arrList.add("E");
        System.out.println("Size after additions: " + arrList.size());

        arrList.remove(0);
        System.out.println("Size after deletions: " + arrList.size());
        System.out.println("Contents: " + arrList);

        System.out.println("Print arraylist contents using iterator: ");
        Iterator itr = arrList.listIterator();
        while (itr.hasNext()) {
            Object element = itr.next();
            System.out.print(element + " ");
        }
    }
}

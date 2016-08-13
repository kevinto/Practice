import java.util.Arrays;
import java.util.Comparator;
import java.util.*;
import java.util.regex.Pattern;


/**
 * Created by kevint on 4/27/2016.
 */
public class Explore {
    public static void main(String[] args) {
        Integer[] arr = { 3, 6, 4, 7};
        System.out.println("Unsorted: " + Arrays.toString(arr));

        Arrays.sort(arr);
        System.out.println("Asc Sort: " + Arrays.toString(arr));

        Arrays.sort(arr, new reverse());
        System.out.println("Desc Sort: " + Arrays.toString(arr));
    }

    public static class reverse implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            return i2.compareTo(i1);
        }
    }
}

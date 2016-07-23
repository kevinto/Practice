import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kevint on 7/18/2016.
 */
public class Solution {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(5000);
        arr.add(5000);
        arr.add(5000);

        int res = removeDuplicates(arr);
        return;
    }

    public static int removeDuplicates(ArrayList<Integer> a) {
        if (a.size() < 2) return a.size();

        int k = 0;
        int i = 1;
        while (i < a.size()) {
            if (a.get(k).equals(a.get(i))) {
                i++;
            } else {
                k++;
                a.set(k, a.get(i));
                i++;
            }
        }

        return k + 1;
    }
}

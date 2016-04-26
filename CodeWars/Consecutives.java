import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kevint on 4/26/2016.
 */
public class Consecutives {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(7);
        list.add(7);
        list.add(3);
        System.out.println(Arrays.toString(sumConsecutive(list).toArray()));
    }

    public static List<Integer> sumConsecutive(List<Integer> s) {
        // null case
        if (s == null) {
            return null;
        }

        // 0 or 1 element list case
        int listSize = s.size();
        if (listSize < 2 ) {
            return s;
        }

        // Consecutive code
        int count = 0;
        ArrayList<Integer> retArray = new ArrayList<>();
        Integer previous = s.get(0);
        for (Integer element : s) {
            if (!element.equals(previous)) {
                retArray.add(count * previous);
                count = 0;
            }
            count ++;
            previous = element;
        }
        retArray.add(count * previous);
        return retArray;
    }
}

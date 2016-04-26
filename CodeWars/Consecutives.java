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
        if (s == null) {
            return null;
        }

        if (s.size() < 2) {
            return s;
        }

        ArrayList<Integer> retList = new ArrayList<>();
        int previous = s.get(0);
        int count = 0;
        for (int element : s) {
            if (!(previous == element)) {
                retList.add(previous * count);
                count = 0;
            }
            count++;
            previous = element;
        }
        retList.add(previous * count);

        return retList;
    }
}

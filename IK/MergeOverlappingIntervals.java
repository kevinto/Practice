import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by kevinto on 12/18/16.
 */
public class MergeOverlappingIntervals {
    public static void main(String[] args) {
        ArrayList<Interval> list = new ArrayList<>();
        //list.add(new Interval(6, 8));
        //list.add(new Interval(1, 3));
        //list.add(new Interval(5, 7));
        //list.add(new Interval(2, 4));

        list.add(new Interval(6, 8));
        list.add(new Interval(1, 9));
        list.add(new Interval(2, 4));
        list.add(new Interval(4, 7));

        ArrayList<Interval> result = merge(list);
        print(result);
    }

    public static ArrayList<Interval> merge(ArrayList<Interval> oList) {
        if (oList == null) {
            return null;
        }

        ArrayList<Interval> res = new ArrayList<>();
        Collections.sort(oList);

        for (int i = 0; i < oList.size(); i++) {
            Interval curr = oList.get(i);
            int currStart = curr.start;
            int currEnd = curr.end;

            for (int j = i + 1; j < oList.size(); j++) {
                Interval next = oList.get(j);
                if (currStart <= next.start && currEnd >= next.start) {
                    currEnd = Math.max(currEnd, next.end);
                    i = j;
                } else {
                    break;
                }
            }
            res.add(new Interval(currStart, currEnd));
        }

        return res;
    }

    public static void print(ArrayList<Interval> oList) {
        if (oList == null) {
            return;
        }

        for (Interval i : oList) {
            System.out.println("start: " + i.start + ", end: " + i.end);
        }
    }

    static class Interval implements Comparable<Interval> {
        int start;
        int end;

        Interval(int s, int e) {
            this.start = s;
            this.end = e;
        }

        @Override
        public int compareTo(Interval i2) {
            return this.start - i2.start;
        }
    }
}

import java.util.*;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        ArrayList<Interval> list = new ArrayList<>();
        //list.add(new Interval(6, 8));
        //list.add(new Interval(1, 3));
        //list.add(new Interval(5, 7));
        //list.add(new Interval(2, 4));

        list.add(new Interval(1, 3));
        list.add(new Interval(3, 5));
        list.add(new Interval(6, 10));
        list.add(new Interval(9, 15));
        list.add(new Interval(100, 101));

        List<Interval> result = merge(list);
        for (int i = 0; i < result.size(); i++) {
            Interval curr = result.get(i);
            System.out.println(curr.Start + ", " + curr.End);
        }
    }

    // 1. sort by starting time increasing. Use stack to track current interval. If top of stack can be expended by current interval, expend it. If current interval cant merge, push it to stack.
    public static List<Interval> merge(ArrayList<Interval> oList) {
        List<Interval> res = new ArrayList<>();
        if (oList == null || oList.size() < 2) {
            return res;
        }

        Collections.sort(oList, Comparator.comparingInt(a -> a.Start));

        Stack<Interval> stack = new Stack<>();
        Interval firstElement = oList.get(0);
        stack.push(new Interval(firstElement.Start, firstElement.End));

        for (int i = 1; i < oList.size(); i++) {
            Interval currTop = stack.peek();
            Interval currElement = oList.get(i);

            if (currTop.Start <= currElement.Start
                    && (currTop.End >= currElement.End || currElement.Start <= currTop.End) ) {
                currTop.End = Math.max(currTop.End, currElement.End);
            } else {
                stack.push(new Interval(currElement.Start, currElement.End));
            }
        }

        return new ArrayList<Interval>(stack);
    }

    private static class Interval {
        public int Start;
        public int End;

        Interval(int start, int end) {
            this.Start = start;
            this.End = end;
        }
    }
}


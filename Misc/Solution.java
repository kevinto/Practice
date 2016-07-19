import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kevint on 7/18/2016.
 */
public class Solution {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1000441);
        list1.add(1000441);
        list1.add(2);
        System.out.print(list1.get(0) == list1.get(1));
//        list1.add(3);
//        list1.add(5);
//        list1.add(7);
        System.out.print(new Solution().repeatedNumber(list1));
    }

    public int repeatedNumber(final List<Integer> a) {
        if (a == null || a.size() < a.size() / 3) return -1;

        Collections.sort(a);
        for (int i = 0; i < a.size(); i++) {
            int freq = 1;
            while (i + 1 < a.size() && a.get(i) == a.get(i+1)) {
                freq++;
                i++;
            }

            if (freq * 3 > a.size()) {
                return a.get(i);
            }
        }

        return -1;
    }

    public int getCount(List<Integer> a, int num) {
        Iterator<Integer> itr = a.iterator();
        int currCount = 0;
        while (itr.hasNext()) {
            int curr = itr.next();
            if (curr == num) currCount++;
        }

        return currCount;
    }

    public void addNum(ArrayList<Data> tempMap, int num) {
        // Check if in map already
        for (int i = 0; i < tempMap.size(); i++) {
            Data currData = tempMap.get(i);
            if (currData.num == num) {
                currData.count++;
                return;
            }
        }

        // decreament everyone
        // if 0 remove, and add new
        boolean isSet = false;
        for (int i = 0; i < tempMap.size(); i++) {
            Data currData = tempMap.get(i);
            currData.count--;
            if (currData.count == 0 && !isSet) {
                tempMap.set(i, new Data(num, 1));
                isSet = true;
            }
        }
    }

    class Data {
        int num;
        int count;
        public Data(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
}

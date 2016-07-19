import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kevin on 7/19/16.
 */
public class QuadCombination {
    public static void main(String[] args) {
        int[] test1 = {1,1,1,1};
        int target = 4;
        List<Integer> result = new QuadCombination().findIndexes(test1, target);
        System.out.println(result);
        return;
    }

    public List<Integer> findIndexes(int[] arr, int target) {
        List<Integer> result = new ArrayList<>();
        if (arr == null || arr.length < 4) return result;

        int length = arr.length;
        HashMap<Integer, ArrayList<Pair>> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            for (int k = i + 1; k < length; k++) {
                int sum = arr[i] + arr[k];
                Pair newPair = new Pair(i, k);

                if (map.containsKey(sum)) {
                    ArrayList<Pair> pairList = map.get(sum);
                    pairList.add(newPair);
                } else {
                    ArrayList<Pair> newPairList = new ArrayList<>();
                    newPairList.add(newPair);
                    map.put(sum, newPairList);
                }
            }
        }

        populateResults(result, map, target);
        return result;
    }

    public void populateResults(List<Integer> result, HashMap<Integer, ArrayList<Pair>> map, int target) {
        for (int key : map.keySet()) {
            int complement = target - key;
            if (map.containsKey(complement)) {
                ArrayList<Pair> keyList = map.get(key);
                ArrayList<Pair> complementList = map.get(complement);

                for (Pair p1 : keyList) {
                    for (Pair p2: keyList) {
                        if (!p1.equals(p2)) {
                            p1.addTo(result);
                            p2.addTo(result);
                            return;
                        }
                    }
                }
            }
        }
    }

    class Pair {
        int index1;
        int index2;

        Pair (int i1, int i2) {
            this.index1 = i1;
            this.index2 = i2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;

            Pair p = (Pair) o;

            return (this.index1 == p.index1 || this.index2 == p.index2) ||
                    (this.index1 == p.index2 || this.index2 == p.index1);
        }

        public void addTo(List<Integer> list) {
            list.add(this.index1);
            list.add(this.index2);
        }
    }
}

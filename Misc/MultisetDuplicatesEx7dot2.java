import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Kevin on 6/26/16.
 */
public class MultisetDuplicatesEx7dot2 {
    public static void main(String[] args) {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(1);
        test.add(2);
        test.add(2);

        ArrayList<ArrayList<Integer>> res = getPerm(test);
        return;
    }

    public static ArrayList<ArrayList<Integer>> getPerm(ArrayList<Integer> originalSet) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (originalSet.size() == 0) {
            return res;
        }

        ArrayList<Integer> path = new ArrayList<>();
        getPerm(originalSet, res, path);
        return res;
    }

    public static void getPerm(ArrayList<Integer> originalSet, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> path) {
        if (path.size() == originalSet.size()) {
            res.add(new ArrayList<>(path));
        } else {
            for (int candidate : candidates(originalSet, path)) {
                path.add(candidate);
                getPerm(originalSet, res, path);
                path.remove(path.size() - 1);
            }
        }
    }

    public static ArrayList<Integer> candidates(ArrayList<Integer> originalSet, ArrayList<Integer> path) {
        ArrayList<Integer> res = new ArrayList<>();

        for (int num : originalSet) {
            res.add(num);
        }

        for (int num : path) {
            res.remove((Integer)num);
        }

        HashSet<Integer> nonDuplicates = new HashSet<>();
        for (int num : res) {
            nonDuplicates.add(num);
        }
        res = new ArrayList<>(nonDuplicates);
        return res;
    }
}

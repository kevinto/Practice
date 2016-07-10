import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Kevin on 7/9/16.
 * Combination sum problem.
 */
public class CombinationSum {
    public static void main(String[] args) {
        ArrayList<Integer> testArr = new ArrayList<>();
        testArr.add(2);
        testArr.add(3);
        testArr.add(6);
        testArr.add(7);
        int target = 7;

        System.out.println(new CombinationSum().combinationSum(testArr, target));
    }

    private ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> arr, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (arr == null || arr.size() == 0) {
            return res;
        }

        Collections.sort(arr);
        ArrayList<Integer> path = new ArrayList<>();
        combinationSum(arr, target, path, 0, res);

        return res;
    }

    private void combinationSum(ArrayList<Integer> arr, int target, ArrayList<Integer> path,
                                int tracker, ArrayList<ArrayList<Integer>> res) {
        if (target == 0) {
            if (!res.contains(path)) {
                res.add(new ArrayList<>(path));
            }
        } else if (target < 0) {
            return;
        }

        for (int i = tracker; i < arr.size(); i++) {
            if (arr.get(i) > target) return;

            path.add(arr.get(i));
            combinationSum(arr, target - arr.get(i), path, i, res);
            path.remove(path.size() - 1);
        }
    }


}

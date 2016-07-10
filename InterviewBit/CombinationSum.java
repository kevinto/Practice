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
            res.add(new ArrayList<>(path));
        } else if (target < 0) {
            return;
        }

        // How does tracker remove the duplicate numbers?
            // With the tracker we can still try the same number multiple times.
            // Like 2-2-2. The tracker enables us to try all the combinations using the
            // same number multiple numbers plus a new number. Like 2-2-2-3, 2-2-3, 2-3,
            // We will not use the 2 again after we use the 3. This makes sure that we
            // have unique combinations.
        for (int i = tracker; i < arr.size(); i++) {
            if (arr.get(i) > target) return;

            path.add(arr.get(i));
            combinationSum(arr, target - arr.get(i), path, i, res);
            path.remove(path.size() - 1);
        }
    }


}

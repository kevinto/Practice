import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {
        ArrayList<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        permute(nums, path, res);
        return res;
    }

    public static void permute(int[] nums, ArrayList<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            List<Integer> newList = new ArrayList<>();
            for (int num : path) {
                newList.add(num);
                res.add(newList);
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!path.contains(nums[i])) {
                path.add(nums[i]);
                permute(nums, path, res);
                path.remove(path.size() - 1);
            }
        }
    }
}


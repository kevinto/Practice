import java.util.HashSet;

/**
 * Created by kevinto on 3/6/17.
 */
public class LongestConIntsSubseq {
    /*
    Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

    For example,
    Given [202, 201, 100, 4, 200, 1, 3, 2, 203, 204],
    The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

    Your algorithm should run in O(n) complexity.

    public int longestConsecutive(int[] nums) {}

    ideas:
            1. gen all poss subseq and check if conseq
    o(2^n) * o(n)
2. sort -> o(nlogn) + o(n^2)
3. load into hashset. o(n). go through all poss starts. o(n^2)
            4. load into hashset. o(n). check start + length - 1. o(n^2) worst case, but optimized to be less then n2.
5. possible starts. min number of a subsequence. occurs before end. unsorted can occur anywhere.
            - multi-size buckets

    noncon
    all con increasing
    all con desc
            mixed

    public int longestConsecutive(int[] nums) {
        // TODO: input checking

        int max = 1;
        HashSet<Integer> set = loadSet(nums);

        for (int val : nums) {
            if (set.contains(val - 1)) {
                continue;
            }

            int currLen = 1;
            int currVal = val + 1;
            while (set.contains(currVal)){
                currLen++;
                currVal++;
            }

            max = Math.max(max, currLen);
        }

        return max;
    }
*/
}

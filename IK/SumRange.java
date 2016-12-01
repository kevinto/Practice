/**
 * Created by kevinto on 11/30/16.
 *
 * http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 */
public class SumRange {
    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 7, 9, 11};
        int n = arr.length;
        SegmentTree tree = new SegmentTree(arr, n);

        return;
    }

    static class SegmentTree {
        int[] segmentTreeNodes;

        SegmentTree(int[] arr, int n) {
            // Allocate memory for segment tree
            //Height of segment tree
            int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));

            //Maximum size of segment tree
            int max_size = 2 * (int) Math.pow(2, x) - 1;

            segmentTreeNodes = new int[max_size]; // Memory allocation

            constructSTUtil(arr, 0, n - 1, 0);
        }

        // This constructs the segment tree by saying that the sum is made up of
        // the sum of by left + right child.
        int constructSTUtil(int[] arr, int start, int end, int pos) {
            if (start == end) {
                // There is only 1 element. Store it at the pos.
                segmentTreeNodes[pos] = arr[start];
                return arr[start];
            }

            // If there are more than one elements, then recur for left and right
            // subtrees and store the sum of values in this node.
            int mid = ((end - start) / 2) + start;
            segmentTreeNodes[pos] = constructSTUtil(arr, start, mid, pos * 2 + 1) +
                    constructSTUtil(arr, mid + 1, end, pos * 2 + 2);
            return segmentTreeNodes[pos];
        }
    }
}

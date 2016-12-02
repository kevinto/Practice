/**
 * Created by kevinto on 11/30/16.
 *
 * http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 */
public class SumRange {
    public static void main(String[] args) {
        // For 6
//        int[] arr1 = {1, 3, 5, 7, 9, 11};
//        int n = arr1.length;
//        SegmentTree tree = new SegmentTree(arr1, n);

        // For 8
        int[] arr2 = {1, 3, 5, 7, 9, 11, 12, 13};
        SegmentTree tree2 = new SegmentTree(arr2, arr2.length);

        // For 9
        int[] arr3 = {1, 3, 5, 7, 9, 11, 12, 13, 14};
        SegmentTree tree3 = new SegmentTree(arr3, arr3.length);

        // There is a jump between 8 and 9
        System.out.println("8 = " + (int) (Math.ceil(Math.log(8) / Math.log(2))));
        System.out.println("9 = " + (int) (Math.ceil(Math.log(9) / Math.log(2))));
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

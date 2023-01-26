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
        int[] arr3 = {1, 3, 5, 7, 9, 11};
        SegmentTree tree3 = new SegmentTree(arr3, arr3.length);

        // There is a jump between 8 and 9
        System.out.println("8 = " + (int) (Math.ceil(Math.log(8) / Math.log(2))));
        System.out.println("9 = " + (int) (Math.ceil(Math.log(9) / Math.log(2))));
        return;
    }

    static class SegmentTree {
        // Remember this represents the segment tree. The first node is the sum of the whole tree.
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
                // We reached one of the base elements seg tree,
                // So store a real (not sum) value in it.
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

        // Return sum of elements in range from index startIndex (quey start) to
        // endIndex (query end).  It mainly uses getSumUtil()
        int getSum(int n, int startIndex, int endIndex)
        {
            // Check for erroneous input values
            if (startIndex < 0 || endIndex > n - 1 || startIndex > endIndex) {
                System.out.println("Invalid Input");
                return -1;
            }
            return getSumUtil(0, n - 1, startIndex, endIndex, 0);
        }

        /*
        A recursive function to get the sum of values in given range
        of the array. The following are parameters for this function.
            segmentTreeNodese    --> Pointer to segment tree
            pos    --> Index of current node in the segment tree. Initially
                    0 is passed as root is always at index 0
            start & end  --> Starting and ending indexes of the segment represented
                        by current node, i.e., st[si]
            queryStart & queryEnd  --> Starting and ending indexes of query range
        */
        int getSumUtil(int currRangeStart, int currRangeEnd, int queryStart, int queryEnd, int pos)
        {
            // If segment of this node is a part of given range, then return
            // the sum of the segment. This means the query overlaps this range.
            if (queryStart <= currRangeStart && queryEnd >= currRangeEnd)
                return segmentTreeNodes[pos];

            // If segment of this node is outside the given range
            if (currRangeEnd < queryStart || currRangeStart > queryEnd)
                return 0;

            // If a part of this segment overlaps with the given range
            int mid = currRangeStart + ((currRangeEnd - currRangeStart)/ 2);
            return getSumUtil(currRangeStart, mid, queryStart, queryEnd, 2 * pos + 1) +
                    getSumUtil(mid + 1, currRangeEnd, queryStart, queryEnd, 2 * pos + 2);
        }
    }
}

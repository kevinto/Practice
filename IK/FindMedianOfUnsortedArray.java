import java.util.PriorityQueue;

/**
 * Created by kevinto on 1/10/17.
 *
 * Useful Link: http://cs.stackexchange.com/questions/1914/to-find-the-median-of-an-unsorted-array
 */
public class FindMedianOfUnsortedArray {
    public static int findMedQs(int[] nums) {
        int half = nums.length / 2;
        int medPos = nums.length % 2 == 0 ? half - 1 : half;
        int start = 0;
        int end = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {
            int pivot = nums[start];
            int pivotIdx = start;
            int oldStart = start;
            int oldEnd = end;

            while (start <= end) {
                while (start <= end && nums[start] <= pivot) {
                    start++;
                }

                while (start <= end && nums[end] > pivot) {
                    end--;
                }

                if (start <= end) {
                    swap(nums, start, end);
                    start++;
                    end--;
                }
            }

            swap(nums, end, pivotIdx);

            if (end == medPos) {
                break;
            } else if (end < medPos) {
                start = end + 1;
                end = oldEnd;
            } else if (end > medPos) {
                start = oldStart;
                end = end - 1;
            }
        }

        return nums[end];
    }

    private static void swap(int[] nums, int i1, int i2) {
        int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }

    public static int findMedianHeaps(int[] nums) {
        // TODO: input checking

        int heapSize = (nums.length / 2) + 1;
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int val : nums) {
            queue.offer(val);
        }

        while (queue.size() > heapSize) {
            queue.poll();
        }

        return queue.poll();
    }

    /*
    // My impl that deals with even len arr returning the mean of 2 mids
    double specialFindMed(int[] nums,  int start, int end) {
        if (start >= end) {
            return 0;
        }

        boolean isEven = nums.length % 2 == 0;
        boolean isOdd = !isEven;
        int firstMid = nums.length / 2; // Represents the odd mid and the second mid in the even case
        int secondMid = (nums.length / 2) - 1;
        int pivotPos = partitionLeftMostElement(nums, start, end);

        if (firstMid == pivotPos && isOdd) {
            return (double)nums[pivotPos];
        } else if (firstMid == pivotPos && isEven) {
            double result = specialFindMed(nums, start, pivotPos - 1);
            return (result + nums[pivotPos]) / 2.0;
        } else if (secondMid == pivotPos && isEven) {
            double result = specialFindMed(nums, pivotPos + 1, end);
            return (result + nums[pivotPos]) / 2.0;
        } else if (firstMid < pivotPos) {
            return specialFindMed(nums, start, pivotPos - 1);
        } else { // firstMid > pivotPos) {
            return specialFindMed(nums, pivotPos + 1, end);
        }
    }
     */
}

/*
if p = r
    return A[p]

q <- Randomized-Partition(A,p,r)
k <- q-p+1 	the size of the left partition

if i=k		then the pivot value is the answer
	return A[q]
else if i < k	then the answer is in the front
	return Randomized-Select(A,p,q-1,i)
else		then the answer is in the back half
	return Randomized-Select(A,q+1,r,i-k)

 */

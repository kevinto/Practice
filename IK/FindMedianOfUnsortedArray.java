/**
 * Created by kevinto on 1/10/17.
 *
 * Useful Link: http://cs.stackexchange.com/questions/1914/to-find-the-median-of-an-unsorted-array
 */
public class FindMedianOfUnsortedArray {
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

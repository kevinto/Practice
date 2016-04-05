public class BinarySearch {
    private static int data[] = {1, 2, 3, 4, 5, 6, 7, 8};
    private static int size = data.length;

    public static void main(String[] args) {
        System.out.println("Running tests for binary search iterative:");
        System.out.println(binarySearchIterative(1) );
        System.out.println(binarySearchIterative(8));
        System.out.println(binarySearchIterative(2));
        System.out.println(binarySearchIterative(7));

        System.out.println("Running tests for binary search recursive:");
        System.out.println(binarySearchRecursive(1, 0, size - 1));
        System.out.println(binarySearchRecursive(8, 0, size - 1));
        System.out.println(binarySearchRecursive(2, 0, size - 1));
        System.out.println(binarySearchRecursive(7, 0, size - 1));
    }

    // Reference: http://www.cs.toronto.edu/~reid/search/bincode.html
    private static boolean binarySearchIterative(int key) {
        int low = 0;
        int high = size - 1;

        while (high >= low) {
            int middle = (low + high) / 2;

            if(data[middle] == key) {
                return true;
            }
            if (data[middle] < key) {
                low = middle + 1;
            }
            if (data[middle] > key) {
                high = middle - 1;
            }
        }

        return false;
    }

    private static boolean binarySearchRecursive(int key, int start, int end) {
        int middle = (start + end) / 2;

        if (end < start) {
            return false;
        }

        if(data[middle] == key) {
            return true;
        }
        else if (data[middle] < key) {
            return binarySearchRecursive(key, middle + 1, end);
        }
        else {
            return binarySearchRecursive(key, start, middle - 1);
        }
    }
}
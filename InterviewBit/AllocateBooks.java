import java.util.ArrayList;

/**
 * Created by Kevin on 7/23/16.
 */
public class AllocateBooks {
    public static void main(String[] args) {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(12);
        test.add(34);
        test.add(67);
        test.add(90);
        System.out.println(books(test, 2));
    }

    //
    public static int books(ArrayList<Integer> books, int students) {
        int studs = students;
        long high = Long.MAX_VALUE;
        long low = 0;
        long mid, res = 0;
        long sum = 0;

        if (students > books.size()) return -1;

        sum = findSum(books);

        while (low <= high) {
            mid = low + ((high - low) / 2);

            // Is possible checks if mid page amount is possible
            if (isPossible(books, students, mid, sum)) {
                System.out.println("possible: " + mid + "(mid) - " + low + "(low) - " + high + "(high)");
                res = mid;
                high = mid - 1;
            } else {
                System.out.println("not possible: " + mid + "(mid) - " + low + "(low) - " + high + "(high)");
                low = mid + 1;
            }
        }

        return (int)res;
    }

    public static int findSum(ArrayList<Integer> books) {
        int sum = 0;
        for (int i = 0; i < books.size(); i++) {
            sum += books.get(i);
        }
        return sum;
    }

    public static boolean isPossible(ArrayList<Integer> books,
                                     int students, long maxPage, long totalPages) {
        if (maxPage < totalPages / students) {
            return false;
        }

        int index = 0;
        int n = books.size();
        int i;

        for (i = 0; i < students && index < n; i++) {
            long total = 0;

            while (total < maxPage && index < n) {
                total += books.get(index);
                if (total > maxPage) {
                    break;
                }
                index++;
            }
        }

        // Why does this return false?
        // Returning false because the picked maxPage is too low. The books picked
        // added together are bigger than maxPage. Above we went through each student
        // and kept track of the book index to see if each student's book interval
        // stays less than the maxPage. IF it goes over the max pages than we
        // are too low because we are trying to find the smallest max of the interval.
        if (index < n) {
            return false;
        }

        // Returning true because this maxPages value can be valid with the number of
        // books and number of students. We will keep narrowing down the range under we find
        // the best possible.
        return true;
    }
}

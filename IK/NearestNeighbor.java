import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinto on 11/20/16.
 */
public class NearestNeighbor {
    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 1));
        points.add(new Point(3, 3));
        points.add(new Point(4, 4));
        points.add(new Point(5, 5));
        points.add(new Point(6, 6));
        points.add(new Point(7, 7));

        Point center = new Point(0, 0);
        int k = 3;
        List<Point> results = getNearestK(center, points, k);
        System.out.println("Center - " + center.toString());

        for (Point p : results) {
            System.out.println("Closest - " + p.toString());
        }
        return;
    }

    static List<Point> getNearestK(Point center, List<Point> points, int k) {
        List<Point> results = new ArrayList<>();
        if (points == null || points.size() == 0) {
            return results;
        }

        quickSort(points, center, k, 0, points.size() - 1);
        for (int i = 0; i < k; i++) {
            results.add(points.get(i));
        }

        return results;
    }

    static void quickSort(List<Point> points, Point center, int k, int start, int end) {
        if (start < end) {
            int pivotIndex = partition(points, center, start, end);
            if (pivotIndex == k) { // No need to move on
                return;
            } else if (pivotIndex < k) { // Don't care above sorting anything before our kth element
                quickSort(points, center, k, pivotIndex + 1, end);
            } else if (pivotIndex > k){ // Don't care about sorting anyhting after our kth element
                quickSort(points, center, k, start, pivotIndex - 1);
            }
        }
    }

    static int partition(List<Point> points, Point center, int start, int end) {
        int mid = ((end - start) / 2) + start;
        Point pivot = points.get(mid);
        int low = 0;
        int high = end;

        while (low < high) {
            while (points.get(low).compareTo(center, pivot) == -1) {
                low++;
            }

            while (points.get(high).compareTo(center, pivot) == 1) {
                high--;
            }

            if (low < high) {
                Point temp = points.get(low);
                points.set(low, points.get(high));
                points.set(high, temp);
                low++;
                high--;
            }
        }

        return low;
    }

    static class Point {
        int row;
        int col;

        Point(int x, int y) {
            row = x;
            col = y;
        }

        public int compareTo(Point center, Point anotherPoint) {
            int currDistToCenter = distance(center, this);
            int otherDistToCenter = distance(center, anotherPoint);
            if (currDistToCenter == otherDistToCenter) {
                return 0;
            } else if (currDistToCenter < otherDistToCenter) {
                return -1;
            } else {
                return 1;
            }
        }

        public String toString() {
            return "row: " + row + ", col: " + col;
        }

        private int distance (Point center, Point target) {
            return (int)(Math.pow(center.row - target.row, 2) + Math.pow(center.col - target.col, 2));
        }
    }
}

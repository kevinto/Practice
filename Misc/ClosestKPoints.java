import java.util.ArrayList;
import java.util.Collection;
import java.util.PriorityQueue;

/**
 * Created by Kevin on 7/3/16.
 */
public class ClosestKPoints {
    public static void main(String[] args) {
        Collection<Point> points = new ArrayList<>();
        Point origin = new Point(0, 0);
        points.add(new Point(1, 1, origin));
        points.add(new Point(1, 3, origin));
        points.add(new Point(-1, 1, origin));
        points.add(new Point(-1, 3, origin));
        points.add(new Point(1, -1, origin));
        points.add(new Point(3, -1, origin));
        points.add(new Point(-1, -1, origin));
        points.add(new Point(-1, 3, origin));
        points.add(new Point(2, 2, origin));

        Collection<Point> closestPoints = new ClosestKPoints().getClosest(points, 5);
        System.out.print(closestPoints);
    }

    public static Collection<Point> getClosest(Collection<Point> points, int k) {
        // Create new priority queue with an initial capacity
        PriorityQueue<Point> queue = new PriorityQueue<>(k);


        for (Point point : points) {
            // We are going to save space by only using k spaces in the priority queue
            if (queue.size() < k) {
                // We are going to add everything up till k. We going to start caring
                // after out capacity is filled up.
                queue.offer(point); // Inserts into priority queue
            } else {
                // Top of the heap will be the max dist. If the current point is less than
                // the head, then we are going to remove the head and add the current point.
                if (queue.peek().compareTo(point) < 0) {
                    queue.poll(); // retrieves and removes the head of queue
                    queue.offer(point); // Inserts into priority queue
                }
            }
        }
        System.out.println(queue.peek());
        return queue;
    }
}

class Point implements Comparable<Point>{
    int x, y;
    double distance;

    Point (int x, int y, Point originPoint) {
        this.x = x;
        this.y = y;

        distance = Math.sqrt(Math.pow(x - originPoint.x, 2) + Math.pow(y - originPoint.y, 2));
    }

    Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point that) {
        // This makes the top of the heap a max number. Think of it as
        // you are comparaing the param to curr object. if param wins then
        // put on top in priority
        return Double.valueOf(that.distance).compareTo(distance);
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
    }
}

import java.util.Queue;
import java.util.LinkedList;

/**
 * Created by kevinto on 1/28/17.
 */
public class ShortestDistancePassenger {
    public static void main(String[] args) {
        char[][] matrix = {
                {'d', 'x', 'x', 'x', 'd'},
                {'x', 'x', 'x', 'x', 'x'},
                {'d', 'x', 'x', 'x', 'p'},
                {'x', 'x', 'x', 'x', 'x'},
                {'d', 'x', 'x', 'x', 'x'}
        };
        int[] passPos = {2, 4};
        findClosestDriver(matrix, passPos);
    }

    public static void findClosestDriver(char[][] matrix, int[] passPos) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        Queue<Pos> queue = new LinkedList();
        queue.offer(new Pos(passPos));
        Pos curr = new Pos(-1,-1);
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        while (!queue.isEmpty()) {
            curr = queue.poll();
            visited[curr.row][curr.col] = true;

            if (matrix[curr.row][curr.col] == 'd') {
                break;
            }

            addToQueue(matrix, curr, visited, queue);
        }

        System.out.println("driver coordinates: " + curr.row + ", " + curr.col);
        System.out.println("driver distance: " + curr.distanceFromPassenger);
    }

    private static void addToQueue(char[][] matrix, Pos curr, boolean[][] visited, Queue<Pos> queue) {
        int i = curr.row == 0 ? 0 : curr.row - 1;
        int j = curr.col == 0 ? 0 : curr.col - 1;

        for (; i < matrix.length && i <= curr.row + 1; i++) {
            for (; j < matrix[0].length && j <= curr.col + 1; j++) {
                if (!visited[i][j]) {
                    queue.offer(new Pos(i, j, curr.distanceFromPassenger + 1));
                }
            }
            j = curr.col == 0 ? 0 : curr.col - 1;
        }
    }

    private static class Pos {
        int row;
        int col;
        int distanceFromPassenger;

        Pos(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.distanceFromPassenger = dist;
        }

        Pos(int row, int col) {
            this.row = row;
            this.col = col;
            this.distanceFromPassenger = 0;
        }

        Pos(int[] passPos) {
            this.row = passPos[0];
            this.col = passPos[1];
            this.distanceFromPassenger = 0;
        }
    }
}

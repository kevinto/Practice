import java.util.*;
import java.util.LinkedList;

/**
 * Created by Kevin on 11/10/16.
 */
public class SnakesAndLadders {
    public static void main(String[] args) {
        initBoard();
        System.out.println(minMoves());
    }

    public static int minMoves() {
        Queue<GameTile> queue = new LinkedList();
        boolean[] visited = new boolean[boardSize];
        GameTile start = new GameTile(1);
        queue.offer(start);

        GameTile currentMove = new GameTile(-1);
        while (!queue.isEmpty()) {
            currentMove = queue.poll();
            visited[currentMove.tileNumber] = true;

            if (currentMove.tileNumber == boardSize - 1) {
                break;
            }

            for (int next = currentMove.tileNumber + 1; next <= currentMove.tileNumber + 6 && next < boardSize; next++) {
                if (!visited[next]) {
                    GameTile nextMove = new GameTile(next);
                    nextMove.distance = currentMove.distance + 1;

                    // Reset the tile number if the visitedBoard shows that we are teleporting
                    if (board[nextMove.tileNumber] != 0) {
                        nextMove.tileNumber = board[nextMove.tileNumber];
                    }
                    queue.offer(nextMove);
                }
            }
        }
        return currentMove.distance;
    }

    static class GameTile {
        int tileNumber;
        int distance;

        GameTile(int id) {
            distance = 0;
            tileNumber = id;
        }
    }

    private static int[] board;
    private static final int boardSize = 37;

    public static void initBoard() {
        board = new int[boardSize];
        board[2] = 15;
        board[5] = 7;
        board[9] = 27;
        board[17] = 4;
        board[18] = 29;
        board[20] = 6;
        board[24] = 16;
        board[25] = 35;
        board[32] = 30;
        board[34] = 12;
    }
}

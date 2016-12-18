/**
 * Created by Kevin on 11/12/16.
 */
import java.util.*;
import java.util.LinkedList;

public class KnightsTourShortestPath {
    public static void main(String[] args) {
        getShortestPath(0, 0, 7, 7);
    }

    public static void getShortestPath(int startRow, int startCol, int endRow, int endCol) {
        initBoard();
        System.out.println("minimum moves: " + minMoves(startRow, startCol, endRow, endCol));
//        getPath(startRow, startCol, endRow, endCol);
    }

    private static int minMoves(int startRow, int startCol, int endRow, int endCol) {
        ChessTile start = new ChessTile(startRow, startCol, 0);
        Queue<ChessTile> queue = new LinkedList();
        queue.offer(start);

        ChessTile curr = new ChessTile(-1, -1 , -1);
        while (!queue.isEmpty()) {
            curr = queue.poll();
            visitedBoard[curr.row][curr.col] = true;

            if (curr.row == endRow && curr.col == endCol) {
                break;
            }

            if (validKnightMove(curr.row - 2, curr.col - 1)) {
                queue.offer(new ChessTile(curr.row - 2, curr.col - 1, curr.distance + 1));
            }

            if (validKnightMove(curr.row - 2, curr.col + 1)) {
                queue.offer(new ChessTile(curr.row - 2, curr.col + 1, curr.distance + 1));
            }

            if (validKnightMove(curr.row - 1, curr.col + 2)) {
                queue.offer(new ChessTile(curr.row - 1, curr.col + 2, curr.distance + 1));
            }

            if (validKnightMove(curr.row + 1, curr.col + 2)) {
                queue.offer(new ChessTile(curr.row + 1, curr.col + 2, curr.distance + 1));
            }

            if (validKnightMove(curr.row + 2, curr.col + 1)) {
                queue.offer(new ChessTile(curr.row + 2, curr.col + 1, curr.distance + 1));
            }

            if (validKnightMove(curr.row + 2, curr.col - 1)) {
                queue.offer(new ChessTile(curr.row + 2, curr.col - 1, curr.distance + 1));
            }

            if (validKnightMove(curr.row + 1, curr.col - 2)) {
                queue.offer(new ChessTile(curr.row + 1, curr.col - 2, curr.distance + 1));
            }

            if (validKnightMove(curr.row - 1, curr.col - 2)) {
                queue.offer(new ChessTile(curr.row - 1, curr.col - 2, curr.distance + 1));
            }
        }

        return curr.distance;
    }

    private static boolean validKnightMove(int row, int col) {
        if (row < 0 || col < 0 || row >= visitedBoard.length || col >= visitedBoard[0].length) {
            return false;
        }

        return !visitedBoard[row][col];
    }

    static class ChessTile {
        int row;
        int col;
        int distance;

        ChessTile(int createRow, int createCol, int distanceFromOrigin) {
            row = createRow;
            col = createCol;
            distance = distanceFromOrigin;
        }
    }

    static boolean[][] visitedBoard;
    private static void initBoard() {
        visitedBoard = new boolean[8][8];
    }
}

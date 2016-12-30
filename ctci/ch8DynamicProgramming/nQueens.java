package ch8DynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Kevin on 6/26/16.
 */
public class nQueens {
    public static void main(String[] args) {
        int n = 8;
        ArrayList<ArrayList<String>> validBoards = getValidConfigs(n);
        System.out.println(validBoards);

        System.out.println(nqueensWithArrRepresentingColumns(4));
    }

    public static ArrayList<ArrayList<String>> getValidConfigs(int n) {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        getValidConfigs(n, new ArrayList<>(), res);
        return res;
    }

    public static void getValidConfigs(int n, ArrayList<Integer> path, ArrayList<ArrayList<String>> validBoards) {
        if (n == path.size()) {
            validBoards.add(pathToStringBoard(path));
        } else {
            HashSet<Integer> candidates = getCandidates(n, path);
            for (int candidate : candidates) {
                path.add(candidate);
                getValidConfigs(n, path, validBoards);
                path.remove(path.size() - 1);
            }
        }
    }

    private static HashSet<Integer> getCandidates(int n, ArrayList<Integer> path) {
        HashSet<Integer> candidates = new HashSet<>();
        for (int i = 0; i < n; i++) {
            candidates.add(i);
        }

        if (path.size() == 0) {
            return candidates;
        }

        for (Iterator<Integer> itr = candidates.iterator(); itr.hasNext();) {
            if (notValidMove(itr.next(), path, n)) {
                itr.remove();
            }
        }

        return candidates;
    }

    private static boolean notValidMove(int qPos, ArrayList<Integer> path, int n) {
        // Check Verticals
        for (int num : path) {
            if (num == qPos) {
                return true;
            }
        }

        int front = qPos - 1;
        int back = qPos + 1;
        int row = path.size() - 1;
        while ((front >= 0 || back < n) && row >= 0) {
            int currRowQueenPos = path.get(row);
            if (front == currRowQueenPos || back == currRowQueenPos) {
                return true;
            }

            front--;
            back++;
            row--;
        }
        return false;
    }

    private static ArrayList<String> pathToStringBoard(ArrayList<Integer> path) {
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            int currPos = path.get(i);
            String str = "";
            for (int k = 0; k < path.size(); k++) {
                if (k == currPos) {
                    str += "Q";
                } else {
                    str += ".";
                }
            }
            res.add(str);
        }

        return res;
    }

    public static List<List<Integer>> nqueensWithArrRepresentingColumns(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }

        int[] board = new int[n];
        nqueensWithArrRepresentingColumns(n, board, res, 0);
        return res;
    }

    private static void nqueensWithArrRepresentingColumns(int n, int[] board, List<List<Integer>> res, int currCol) {
        if (currCol >= n) {
            List<Integer> newList = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                newList.add(board[i]);
            }

            res.add(newList);
            return;
        }

        for (int i = 0; i < n; i++) {
            board[currCol] = i;
            if (isValid(board, currCol)) {
                nqueensWithArrRepresentingColumns(n, board, res, currCol + 1);
            }
        }
    }

    private static boolean isValid(int[] board, int currCol) {
        // Cant use the entire board length because we stop the path because we would have to assume that our right neighbor was populated correctly.
        // We are populating left to right columns so we cannot check the rightmost columns of the current pointer yet.
        for (int i = 0; i <= currCol; i++) {
            // Check each col to see if any rows match
            if (i != currCol && board[currCol] == board[i]) {
                return false;
            }

            // Check diagonals
            int rowDiff = Math.abs(board[currCol] - board[i]);
            int colDiff = Math.abs(currCol - i);
            if (i != currCol && rowDiff == colDiff) {
                return false;
            }
        }

        return true;
    }
}

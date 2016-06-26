package ch8DynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Kevin on 6/26/16.
 */
public class nQueens {
    public static void main(String[] args) {
        int n = 4;
        ArrayList<ArrayList<String>> validBoards = getValidConfigs(n);
        return;
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
}

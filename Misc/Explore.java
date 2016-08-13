import java.util.Arrays;
import java.util.Comparator;
import java.util.*;
import java.util.regex.Pattern;


/**
 * Created by kevint on 4/27/2016.
 */
public class Explore {
    public static void main(String[] args) {
        int[] a = {2, 2};
        int[] b = {1, 3};

//        int[] a = {2};
//        int[] b = {1};
        long res = new Explore().count(a, b);
        System.out.print(res);
    }

    int[][] G;
    int[] degree;


    // A DFS, after assigning an arbitrary root, creates the G[][] data
    // structure for the graph:
    void root(int[] a, int[] b, int x, int parent)
    {
        for (int i=0; i<a.length; i++) {
            if (a[i] == x+1 || b[i] == x+1) {
                int y;
                if (a[i] == x+1) {
                    y = b[i] - 1;
                } else {
                    y = a[i] - 1;
                }
                if (y != parent) {
                    G[x][degree[x]++] = y;
                    root(a,b, y,x);
                }
            }
        }
    }

    long[][] mem;
    boolean[][] solvedBefore;

    long rec(int useIt, int x)
    {
        // Did we solve this before? If so, return the saved result.
        if (solvedBefore[useIt][x]) {
            return mem[useIt][x];
        }
        // Else solve it:
        solvedBefore[useIt][x] = true;
        long res = 0;
        if ( useIt == 0) {
            // It is still not decided that we should use this sub-tree

            //Decide to use it:
            res = rec(1, x);

            // Do not use it, but the selected tree must be in one of the children
            // sub-trees:
            for (int i=0; i<degree[x]; i++) {
                res += rec(0, G[x][i] );
            }
        } else {
            // Count the number of sub-trees that use this root.
            res = 1;
            for (int i=0; i<degree[x]; i++) {
                long p = rec(1, G[x][i]);
                res *= ( 1 + p );
            }
        }
        //Store the result
        mem[useIt][x] = res;
        return res;
    }

    long count(int[] a, int[] b)
    {
        int n = a.length + 1;
        degree = new int[n]; // The degree/number of children of each node
        G = new int[n][n];  // G[x][i] is the i-th child of x
        root(a,b, 0, -1); // Create G[][] when using 0 as the root

        mem = new long[2][n];             //holds the results
        solvedBefore = new boolean[2][n]; // remembers if we already evaluated
        // the state.

        // 0 is the root, call the recurrence on it:
        return rec(0, 0) + 1; //The function does not count the empty case.
    }
}

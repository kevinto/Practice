/**
 * Created by Kevin on 6/25/16.
 */
public class SubsetsSkienna {
    private static final int MAX_CANDIDATES = 2;
    public static void main(String[] args) {
        int n = 3;
        printSubsets(n);
    }

    public static void printSubsets(int n) {
        boolean[] subsetPath = new boolean[n + 1]; // We want the to exclude 0 from the sets
        backTrack(subsetPath, 0, n);
    }

    public static void backTrack(boolean[] path, int index, int n) {
        boolean[] candidates = new boolean[MAX_CANDIDATES];
        candidates[0] = true;
        candidates[1] = false;

        if (index == n) {
            output(path);
        } else {
            index++;
            for(int i = 0; i < candidates.length; i++) {
                path[index] = candidates[i];
                backTrack(path, index, n);
            }
        }
    }

    public static void output(boolean[] path) {
        System.out.print("[ ");
        for (int i = 1; i < path.length; i++) {
            if (path[i] == true) {
                System.out.print(i + " ");
            }
        }
        System.out.println("]");
    }
}

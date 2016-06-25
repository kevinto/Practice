import java.util.ArrayList;

/**
 * Created by Kevin on 6/25/16.
 */
public class PermutationSkiienna {
    public static void main(String[] args) {
        int n = 3;
        ArrayList<ArrayList<Integer>> result = perm(n);
        System.out.print(result);
    }

    public static ArrayList<ArrayList<Integer>> perm (int n) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (n == 0) {
            res.add(new ArrayList<>());
            return res;
        }

        ArrayList<Integer> list = new ArrayList<>();
        perm(n, res, list);
        return res;
    }

    public static void perm (int n, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list) {
        if (n == list.size()) {
            res.add(new ArrayList<>(list));
            return;
        }

        ArrayList<Integer> candidates = getCandidates(n, list);
        for (int i = 0; i < candidates.size(); i++) {
            list.add(candidates.get(i));
            perm(n, res, list);
            list.remove(list.size() - 1);
        }
    }

    public static ArrayList<Integer> getCandidates(int n, ArrayList<Integer> list) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!list.contains(i)) {
                res.add(i);
            }
        }
        return res;
    }
}

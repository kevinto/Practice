package ch8DynamicProgramming;

import java.util.ArrayList;

/**
 * Created by Kevin on 6/12/16.
 */
public class PowerSet {
    public static void main(String[] args) {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(2);
        System.out.print(getSubsets(test, 0));
    }

    // Description: Recurse down to the end of the set. Reaching the end, we reach the base case.
    // The base case adds an empty set. Once we reach an empty set, we can start building. The first
    // case after the base case is reached, is the element at the end of the set. We add that element
    // and now we have 2 elements the empty set and the last element. As we go up levels, we make a copy
    // of those elements add the new number to those sets and all the newSets to the allSubsets array.
    public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index) {
        ArrayList<ArrayList<Integer>> allsubsets;
        if (set.size() == index) {
            // Adds an empty set. Only happends when we reach the end of the set.
            // we have an empty set then we build from there.
            allsubsets = new ArrayList<>();
            allsubsets.add(new ArrayList<>());
        } else {
            allsubsets = getSubsets(set, index + 1);
            int item = set.get(index);
            ArrayList<ArrayList<Integer>> moreSubsets = new ArrayList<>();

            // Builds on top of the previous sets while maintaining the previous sets.
            for (ArrayList<Integer> subset : allsubsets) {
                ArrayList<Integer> newSubset = new ArrayList<>();
                newSubset.addAll(subset);
                newSubset.add(item);
                moreSubsets.add(newSubset);
            }
            allsubsets.addAll(moreSubsets);
        }

        return allsubsets;
    }
}

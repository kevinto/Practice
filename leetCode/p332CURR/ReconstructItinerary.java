package p332CURR;

import java.util.*;

/**
 * Created by Kevin on 5/17/16.
 * Problem: https://leetcode.com/problems/reconstruct-itinerary/
 *
 * tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
             Return ["JFK", "MUC", "LHR", "SFO", "SJC"].

 Start always at JFK.

 Basically a graph problem with edges.
 Does one valid itineray mean that there is a path to all nodes?
 You have to deal with cycles too.

 Scan through the tickets and contruct an object for each airport.
 Each object can have an already visited state.

 Start at JFK, find the lex order airport and visit it. Then from
 that airport find the next one in lex order.
 */

public class ReconstructItinerary {
    public static void main(String[] args) {
        String[][] teststr = {{"MUC","LHR"}, {"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}};
        List<String> result = findItinerary(teststr);

//        String[][] teststr1 = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
//        List<String> result1 = findItinerary(teststr1);
        return;
    }

    public static List<String> findItinerary(String[][] tickets) {
        for (String[] ticket : tickets)
            targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
        visit("JFK");
        return route;
    }

    public static Map<String, PriorityQueue<String>> targets = new HashMap<>();
    public static List<String> route = new LinkedList();

    public static void visit(String airport) {
        while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
            visit(targets.get(airport).poll());
        route.add(0, airport);
    }
}

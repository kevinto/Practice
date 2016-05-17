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

    }

    public List<String> findItinerary(String[][] tickets) {
        // Construct adjacency list contructed via hash map
        // because we only need the first element
        HashMap<String, String> map = new HashMap<>();
        for (String[] tix : tickets) {
            if (!map.containsKey(tix[1])) {
                map.put(tix[1], tix[2]);
            }
            else {
                List<String> list = new ArrayList<String>();
                list.add(map.get(tix[1]));
                list.add(tix[2]);
                java.util.Collections.sort(list);
                map.put(tix[1], list.get(0));
            }
        }

        // Find the element position of JFK
        // Add JFK to the queue
        List<String> itinerary = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        q.add(map.get("JFK"));
        String currAirPort;

        // While queue is not empty
            // dequeue first element and add it to the return list
            // Add the first dest of added element to the list
        while (!q.isEmpty()) {
            currAirPort = q.remove();
            if (currAirPort != null) {
                itinerary.add(currAirPort);
                q.add(map.get(currAirPort));
            }
        }

        return itinerary;
    }
}

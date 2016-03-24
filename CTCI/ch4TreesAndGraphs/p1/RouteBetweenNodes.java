import java.util.*;

public class RouteBetweenNodes {
    public static void main (String args[]) {
        ArrayList<ArrayList<Integer>> adjLists = new ArrayList<ArrayList<Integer>>();
        
        for(int i = 0; i < 6; i++) {
            adjLists.add(new ArrayList<Integer>());
        }
        
        // insert neighbors into list for vertex 0 
        adjLists.get(0).add(1);
        adjLists.get(0).add(2);
        
        // insert neighbors into list for vertex 1 
        adjLists.get(1).add(2);
        adjLists.get(1).add(3);
        
        // testing
        System.out.println("vertex 0: " + adjLists.get(0));
        System.out.println("vertex 1: " + adjLists.get(1));
    }
    
    // Route Between Nodes: Given a directed graph, design an algorithm 
    // to find out whether there is a route between two nodes.
    // Assume I am given the Adjacency list.
    // Assume I am given the start point and the end point.
    public static void routeExists(String grid, String start, String end) {
        // Data structure: Adjacency list implemented with a linked list
        
        // Add start to an empty queue
        
        // While the queue is not empty:
            // Set the current element to a dequeued
            
            // for each node n that is adjacent to current:
                // if node is what we are looking for
                    // return true
                // if node is unvisited
                    // add n to the queue
        return;
    } 
}

import java.util.*;

public class RouteBetweenNodes {
    public static void main (String args[]) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        
        for(int i = 0; i < 6; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        
        // insert neighbors into list for vertex 0 
        adjList.get(0).add(1);
        
        // insert neighbors into list for vertex 1 
        adjList.get(1).add(2);
        
        // insert neighbors into list for vertex 2
        adjList.get(2).add(0);
        adjList.get(2).add(3);
        
        // testing
        System.out.println("vertex 0: " + adjList.get(0));
        System.out.println("vertex 1: " + adjList.get(1));
        System.out.println("vertex 2: " + adjList.get(2));
        
        routeExists(adjList, 0, 3);        
    }
    
    // Route Between Nodes: Given a directed graph, design an algorithm 
    // to find out whether there is a route between two nodes.
    // Assume I am given the Adjacency list.
    // Assume I am given the start point and the end point.
    public static void routeExists(ArrayList<ArrayList<Integer>> adjList, int start, int end) {
        // Add start to an empty queue. Here the linked list implementation is already a queue.
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        // System.out.println(queue.size());
        
        // While the queue is not empty:
            // Set the current element to a dequeued
            
            // for each node n that is adjacent to current:
                // if node is what we are looking for
                    // return true
                // if node is unvisited
                    // add n to the queue
        while(queue.size() > 0) {
            int currentElement = queue.remove();
            // System.out.println(currentElement);
            break;
        }
        return;
    } 
}

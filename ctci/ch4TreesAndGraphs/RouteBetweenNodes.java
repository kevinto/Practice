package ch4TreesAndGraphs;

/*
 * Problem 4.1 Statement:
 * Route Between Nodes: Given a directed graph, design an algorithm to find 
 * out whether there is a route between two nodes. 
 */

import java.util.*;

public class RouteBetweenNodes {
    public static void main (String args[]) {
        TestSimpleConnectedPath();
        TestSimpleNonConnectedPath();
    }
    
    public static boolean routeExists(ArrayList<ArrayList<Integer>> adjList, int start, int end) {
        if (start == end) {
            return true;
        } 
        
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        
        boolean[] visited = new boolean[adjList.size()];
        
        while(queue.size() > 0) {
            int currentElement = queue.remove();
            ArrayList<Integer> adjNodes = adjList.get(currentElement);
            
            for(int i = 0; i < adjNodes.size(); i++) {
                int currAdjNode = adjNodes.get(i);
                
                // Need to mark visited nodes because if we don't then
                // We might revisit the node again if there is a cycle 
                // in the graph.
                if (visited[currAdjNode]) {
                    continue;
                }
                else {
                    visited[currAdjNode] = true;
                }
                
                if (currAdjNode == end) {
                    return true;
                }
                
                queue.add(currAdjNode);
            }
        }
        
        return false;
    } 
    
    public static void TestSimpleConnectedPath() {
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
        
        if (routeExists(adjList, 0, 3)) {
            System.out.println("TestSimpleConnectedPath passed!");
        }
        else {
            System.out.println("TestSimpleConnectedPath failed...");
        }
    }
    
    public static void TestSimpleNonConnectedPath() {
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
        
        if (!routeExists(adjList, 0, 4)) {
            System.out.println("TestSimpleNonConnectedPath passed!");
        }
        else {
            System.out.println("TestSimpleNonConnectedPath failed...");
        }
    }
}

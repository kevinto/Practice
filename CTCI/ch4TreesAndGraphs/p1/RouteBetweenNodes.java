import java.util.LinkedList;

public class RouteBetweenNodes {
    public static void main (String args[]) {
        // Implemented an adjacency list
        // AdjacencyList adjList = new AdjacencyList(1);
        // adjList.add(1, 2);
        // adjList.add(1, 3);
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

class AdjacencyList {
    private final LinkedList<Integer>[] adjacencyList; 
    
    public AdjacencyList(int vertices) {
        adjacencyList = (LinkedList<Integer>[]) new LinkedList<Integer>[vertices];
        for(int i = 0; i < adjacencyList.length; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }
    
    public void addEdge(int startVertex, int endVertex) {
        adjacencyList[startVertex].add(endVertex);
    }
    
    public void printAdjacencyList() {
        int i = 0;
        
        for(LinkedList<Integer> list : adjacencyList) {
            System.out.print("adjacencyList[" + i + "] -> ");
            
            for (int edge : list) {
                System.out.print("(" + edge + ")");
            }
            
            i++;
            System.out.println();
        }
    }
}
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
        
        System.out.println("Route exists: " + routeExists(adjList, 0, 3));
    }
    
    public static boolean routeExists(ArrayList<ArrayList<Integer>> adjList, int start, int end) {
        if (start == end) {
            return true;
        } 
        
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        
        while(queue.size() > 0) {
            int currentElement = queue.remove();
            ArrayList<Integer> adjNodes = adjList.get(currentElement);
            
            for(int i = 0; i < adjNodes.size(); i++) {
                int currAdjNode = adjNodes.get(i);
                
                if (currAdjNode == end) {
                    return true;
                }
                
                queue.add(currAdjNode);
            }
        }
        
        return false;
    } 
}

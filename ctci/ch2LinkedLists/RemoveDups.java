package ch2LinkedLists;

/*
 * Problem 2.1 Statement:
 * Remove Dups: Write code to remove duplicates from an unsorted linked list.
 * 
 * Follow Up:
 * 	How would you solve this problem if a temporary buffer is not allowed?
 */

import java.util.*;

public class RemoveDups {
    public static void main(String args[]) {
        LinkedList<String> ll = new LinkedList<String>();
        ll.add("A");
        ll.add("A");
        ll.add("b");
        ll.add("b");
        
        removeDups(ll);
        
        // Should only contain ["A", "b"]
        System.out.println(ll);
    }
    
    public static void removeDups(LinkedList ll) {
        HashMap<Object, Object> hm = new HashMap<Object, Object>();
    
        // The size re-evaluate for every iteration.    
        for (int i = 0; i < ll.size(); i++) {
            Object value = ll.get(i);
            if (hm.containsKey(value)) {
                ll.remove(value);
                
                // Need to decrement to take care of the removed element. If we
                // do not, then we will skip over an element.
                i--;
            }
            else {
                hm.put(value, 0);
            }
        }
    }
}
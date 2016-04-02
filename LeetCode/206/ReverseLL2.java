public class ReverseLL2 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        
        ListNode reversedList = reverse(l1);
        printList(reversedList);   
    }
    
    public static ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        
        if (head.next == null) {
            return head;
        }
        
        ListNode secondElement = head.next;
        head.next = null;
        ListNode reversedList = reverse(secondElement);
        secondElement.next = head;
        
        return reversedList;
    }
    
    public static void printList(ListNode head) {
        if (head == null) {
            return;
        }
        
        if (head.next == null) {
            System.out.println(head.value);
            return;
        }
        
        System.out.println(head.value);
        printList(head.next);
    }
}

class ListNode {
    int value;
    ListNode next;
    public ListNode(int val) { value = val; };
}
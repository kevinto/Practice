import sun.awt.image.ImageWatched;

/**
 * Created by kevinto on 12/3/16.
 */
public class LinkedListIntersection {
    public static void main(String[] args) {
        LinkedListIntersection();
    }

    static int LinkedListIntersection() {
        // Test where both lists are of equal size.
        // 4 -> 5 -> 0 -> 6 -> 1
        LinkedListNode h1 = new LinkedListNode(4);
        h1.next = new LinkedListNode(5);
        h1.next.next = new LinkedListNode(0);
        h1.next.next.next = new LinkedListNode(6);
        h1.next.next.next.next = new LinkedListNode(1);

        // 7 -> 9 -> 0 -> 6 -> 1
        LinkedListNode h2 = new LinkedListNode(7);
        h2.next = new LinkedListNode(9);
        h2.next.next = h1.next.next;

        System.out.println(getIntersection(h1, h2));

        // Test where first list is greater than second list
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6
        LinkedListNode h3 = new LinkedListNode(1);
        h3.next = new LinkedListNode(2);
        h3.next.next = new LinkedListNode(3);
        h3.next.next.next = new LinkedListNode(4);
        h3.next.next.next.next = new LinkedListNode(5);
        h3.next.next.next.next.next = new LinkedListNode(6);

        // 0 -> 8 -> 4 -> 5 -> 6
        LinkedListNode h4 = new LinkedListNode(0);
        h4.next = new LinkedListNode(8);
        h4.next.next = h3.next.next.next;

        System.out.println(getIntersection(h3, h4));

        // Test where second list is greater than first list
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6
        LinkedListNode h6 = new LinkedListNode(1);
        h6.next = new LinkedListNode(2);
        h6.next.next = new LinkedListNode(3);
        h6.next.next.next = new LinkedListNode(4);
        h6.next.next.next.next = new LinkedListNode(5);
        h6.next.next.next.next.next = new LinkedListNode(6);

        // 0 -> 8 -> 4 -> 5 -> 6
        LinkedListNode h5 = new LinkedListNode(0);
        h5.next = new LinkedListNode(8);
        h5.next.next = h6.next.next.next;

        System.out.println(getIntersection(h5, h6));
        return 0;
    }

    static int getIntersection(LinkedListNode root1, LinkedListNode root2) {
        // Check if the last nodes are the same
        if (noIntersection(root1, root2)) {
            return -1;
        }

        // Count the distance between the pointers
        LinkedListNode ptr1 = root1;
        LinkedListNode ptr2 = root2;
        while (ptr1 != null && ptr2 != null) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        if (ptr1 != null) {
            // Find how much to delay the start of root 2.
            int root2Delay = 0;
            while (ptr1 != null) {
                root2Delay++;
                ptr1 = ptr1.next;
            }

            // Delay root 2, so we can find the matching node
            ptr1 = root1;
            ptr2 = root2;
            while (ptr1 != null && ptr2 != null) {
                if (ptr1 == ptr2) {
                    return ptr1.val;
                }

                ptr1 = ptr1.next;
                if (root2Delay <= 0) {
                    ptr2 = ptr2.next;
                } else {
                    root2Delay--;
                }
            }
        } else if (ptr2 != null) {
            // Find how much to delay the start of root 2.
            int root1Delay = 0;
            while (ptr2 != null) {
                root1Delay++;
                ptr2 = ptr2.next;
            }

            // Delay root 2, so we can find the matching node
            ptr1 = root1;
            ptr2 = root2;
            while (ptr1 != null && ptr2 != null) {
                if (ptr1 == ptr2) {
                    return ptr2.val;
                }

                ptr2 = ptr2.next;
                if (root1Delay <= 0) {
                    ptr1 = ptr1.next;
                } else {
                    root1Delay--;
                }
            }
        } else { // Both are null
            ptr1 = root1;
            ptr2 = root2;
            while (ptr1 != null && ptr2 != null) {
                if (ptr1 == ptr2) {
                    return ptr1.val;
                }
                ptr2 = ptr2.next;
                ptr1 = ptr1.next;
            }
        }
        return -1;
    }

    private static boolean noIntersection(LinkedListNode root1, LinkedListNode root2) {
        LinkedListNode last1 = root1;
        LinkedListNode last2 = root2;

        while (last1.next != null) {
            last1 = last1.next;
        }

        while (last2.next != null) {
            last2 = last2.next;
        }

        return last1 != last2;
    }

    static class LinkedListNode {
        int val;
        LinkedListNode next;

        LinkedListNode (int x) {
            val = x;
        }
    }
}

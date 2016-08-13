import sun.jvm.hotspot.debugger.windbg.DLL;

import java.util.HashMap;

/**
 * Created by Kevin on 8/13/16.
 *
 *
 */
public class LRUCache {
    public static void main(String[] args) {
        LRUCache lc = new LRUCache(3);
        lc.set(1, 100);
        lc.set(2, 200);
        lc.set(3, 300);
        lc.set(4, 400);

        System.out.println(lc.get(1));
        System.out.println(lc.get(2));
        System.out.println(lc.get(3));
        System.out.println(lc.get(4));
    }
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode post;
    }

    /**
     * Always add the new node right after head;
     */
    private void addNode(DLinkedNode node){
        node.pre = head;
        node.post = head.post;

        head.post.pre = node;
        head.post = node;
    }

    /**
     * Remove an existing node from the linked list.
     */
    private void removeNode(DLinkedNode node){
        DLinkedNode pre = node.pre;
        DLinkedNode post = node.post;

        pre.post = post;
        post.pre = pre;
    }

    /**
     * Move certain node in between to the head.
     */
    private void moveToHead(DLinkedNode node){
        this.removeNode(node);
        this.addNode(node);
    }

//    // pop the current tail.
//    private DLinkedNode popTail(){
//    }

    // Difference between hashmap and hashtable
    private HashMap<Integer, DLinkedNode> cache = new HashMap<>();
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        head.pre = null;

        tail = new DLinkedNode();
        tail.post = null;

        head.post = tail;
        tail.pre = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }

        this.moveToHead(node);

        return node.value;
    }


    public void set(int key, int value) {
        DLinkedNode node = cache.get(key);

        if (node == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            cache.put(key, newNode);
            this.addNode(newNode);

            count++;
            if (count > capacity) {
                cache.remove(tail.pre.key);
                this.removeNode(tail.pre);
                count--;
            }
        } else {
            node.value = value;
            this.moveToHead(node);
        }
    }
}

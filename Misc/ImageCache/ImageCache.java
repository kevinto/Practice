package ImageCache;

import javax.imageio.ImageIO;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by kevinto on 2/27/17.
 */
public class ImageCache {
    private int cacheCapacityInBytes;
    private int cacheSizeInBytes;
    private Node tail;
    private Node head;
    private HashMap<String, Node> cache;

    public ImageCache (int cacheCapacity) {
        this.cacheCapacityInBytes = cacheCapacity;
        this.cacheSizeInBytes = 0;
        this.cache = new HashMap<>();

        head = new Node("head");
        tail = new Node("tail");
        head.next = tail;
        tail.prev = head;
    }

    public Image get(String key) {
        Node currNode = null;

        if (cache.containsKey(key)) {
            currNode = cache.get(key);
            removeNode(currNode);

            // TODO: Need a another log class when time permits
            System.out.println(currNode.key + " In_CACHE " + currNode.imageSize);
        } else {
            Node newNode = new Node(key);
            getImage(key, newNode);
            cache.put(key, newNode);

            // TODO: Need a another log class when time permits
            System.out.println(currNode.key + " Downloaded " + currNode.imageSize);

            // Check if our cache is too full
            while (cacheSizeInBytes + newNode.imageSize > cacheCapacityInBytes && tail.prev != head) {
                deleleOldest();
            }
        }

        moveToHead(currNode);

        return currNode.image;
    }

    private void deleleOldest() {
        cacheSizeInBytes -= tail.prev.imageSize;
        removeNode(tail.prev);

        //TODO: remove from hashmap also, using a key
    }

    private void moveToHead(Node node){
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node){
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    // Taken from: http://stackoverflow.com/questions/3986891/how-can-i-read-image-from-url-in-java
    private void getImage(String url, Node node) {
        // TODO: Separate out into another class later
        Image image = null;

        try {
            URL urlObj = new URL(url);
            node.image = ImageIO.read(urlObj);

            //TODO: Need more reasearch on how to get size
//            node.imageSize = url.openConnection().getContentLength();
        } catch (Exception ex) {
            // TODO: Log error
        }
    }

    private class Node {
        // TODO: Make getters/setters later
        public Image image;
        public Node next;
        public Node prev;
        public String key;
        public int imageSize;

        Node(String key) {
            next = null;
            prev = null;
            this.key = key;
        }
    }
}

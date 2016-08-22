import java.util.*;

public class Solution {
    class PointsOnAPlane {
        ArrayList<Point> points = new ArrayList<>();
        void addPoint(Point point) {
            points.add(point);
        }

        Collection<Point> findNearest(Point center, int p) {
            ArrayList<Point> res = new ArrayList<>();
            PriorityQueue<Point> pq = new PriorityQueue<>();



            return res;
        }

        class Point {
            final int x;
            final int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int getDist(int x2, int y2) {
                return (int)Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));
            }


        }
    }
    public static void main(String[] args) {
        String test1 = "helloworld";
        Set<String> testdict1 = new HashSet<>();
        testdict1.add("hello");
        testdict1.add("llow");
        testdict1.add("orld");
        System.out.println(wordBreak(test1, testdict1));

        String test2 = "helloworld";
        Set<String> testdict2 = new HashSet<>();
        testdict2.add("hello");
        testdict2.add("he");
        testdict2.add("llow");
        testdict2.add("orld");

        System.out.println(wordBreak(test2, testdict2));
    }

    public static boolean wordBreak(String s, Set<String> dict) {
        boolean[] t = new boolean[s.length()+1];
        t[0] = true; //set first to be true, why?
        //Because we need initial state

        for(int i=0; i<s.length(); i++){
            //should continue from match position
            if(!t[i])
                continue;

            for(String a: dict){
                int len = a.length();
                int end = i + len;
                if(end > s.length())
                    continue;

                if(t[end]) continue;

                if(s.substring(i, end).equals(a)){
                    t[end] = true;
                }
            }
        }

        return t[s.length()];
    }
}

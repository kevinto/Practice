import java.util.*;
import java.util.LinkedList;

/**
 * Created by kevinto on 12/11/16.
 */
public class Skyline {
    public static void main(String[] args) {
        int[][] buildings = {
                {5, 12, 12},
                {15, 20, 10}
//                {2, 9, 10}
        };
        List<int[]> result = getSkylinePriorityQueue(buildings);
        List<int[]> result1 = getSkylineDivideAndConquer(buildings);
        return;
    }

    // ----------- Solution using a Merge Sort Like Divide and Conquer --------------------
    public static List<int[]> getSkylineDivideAndConquer(int[][] buildings) {
        if (buildings.length == 0)
            return new LinkedList();
        return recurSkyline(buildings, 0, buildings.length - 1);
    }

    private static LinkedList<int[]> recurSkyline(int[][] buildings, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;

            LinkedList<int[]> leftList = recurSkyline(buildings, start, mid);
            LinkedList<int[]> rightList = recurSkyline(buildings, mid + 1, end);
            return merge(leftList, rightList);
        } else {
            // When you get here, this means a stand alone building
            LinkedList<int[]> rs = new LinkedList();

            // Add left and height
            rs.add(new int[] { buildings[start][0], buildings[start][2] });

            // Add right/list and end height.
            rs.add(new int[] { buildings[start][1], 0 });
            return rs;
        }
    }

    private static LinkedList<int[]> merge(LinkedList<int[]> leftList, LinkedList<int[]> rightList) {
        LinkedList<int[]> resultList = new LinkedList();

        // How do these heights work?
        // Remember that there are end heights (0) for every building.
        // If two intervals are merged then the end heights will set these two to
        // double zeros so we can put a zero in the results
        int heightLeft = 0, heightRight = 0;

        while (leftList.size() > 0 && rightList.size() > 0) {
            int resultX, resultH;
            if (leftList.getFirst()[0] < rightList.getFirst()[0]) {
                // leftList contains the leftmost coordinate
                resultX = leftList.getFirst()[0];
                heightLeft = leftList.getFirst()[1];
                resultH = Math.max(heightLeft, heightRight);
                leftList.removeFirst();
            } else if (leftList.getFirst()[0] > rightList.getFirst()[0]) {
                // rightList contains the rightMost coordinate
                resultX = rightList.getFirst()[0];
                heightRight = rightList.getFirst()[1];
                resultH = Math.max(heightLeft, heightRight);
                rightList.removeFirst();
            } else {
                // leftList and rightList have the same left coordinate
                // the highest height wins in this case
                resultX = leftList.getFirst()[0];
                heightLeft = leftList.getFirst()[1];
                heightRight = rightList.getFirst()[1];
                resultH = Math.max(heightLeft, heightRight);
                leftList.removeFirst();
                rightList.removeFirst();
            }
            if (resultList.size() == 0 || resultH != resultList.getLast()[1]) {
                // Only add to the results when we have a change in heights
                resultList.add(new int[] { resultX, resultH });
            }
        }
        resultList.addAll(leftList);
        resultList.addAll(rightList);
        return resultList;
    }

    // ----------- Solution using a Max Heap Priority Queue --------------------
    public static List<int[]> getSkylinePriorityQueue(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        List<int[]> heights = new ArrayList<>();

        // b[0] - left
        // b[1] - right
        // b[2] - height
        for(int[] building:buildings) {
            // Add left with negative height
            heights.add(new int[]{building[0], -building[2]});

            // Add right with positive height
            heights.add(new int[]{building[1], building[2]});
        }

        // If left not equal, then sort by left.
        // Else if left is equal, then sort by heights.
        // The heights for the left point are sorted as first.
        Collections.sort(heights, (a, b) -> {
            if(a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        // Initiate a max heap
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);

        int prev = 0;
        for(int[] height:heights) {
            if(height[1] < 0) {
                // This is a left point
                pq.offer(-height[1]);
            } else {
                // This is a right point. Removing by specifying
                // the height that we added before.
                pq.remove(height[1]);
            }
            int curr = pq.peek();
            if(prev != curr) {
                // The max height changed. Add this new height.
                // If the entire heap was emptied, then the top of our heap is 0.
                // The original heap node of 0, will never be removed because we
                // dont have a height of 0.
                result.add(new int[]{height[0], curr});
                prev = curr;
            }
        }
        return result;
    }

    // ----------- Solution using a Tree heap ------------------------------------
    static class BuildingPoint implements Comparable<BuildingPoint> {
        int x;
        boolean isStart;
        int height;

        @Override
        public int compareTo(BuildingPoint o) {
            //first compare by x.
            //If they are same then use this logic
            //if two starts are compared then higher height building should be picked first
            //if two ends are compared then lower height building should be picked first
            //if one start and end is compared then start should appear before end
            if (this.x != o.x) {
                return this.x - o.x;
            } else {
                return (this.isStart ? -this.height : this.height)
                        - (o.isStart ? -o.height : o.height);
            }
        }
    }

    public static List<int[]> getSkyLineUsingHeap(int[][] buildings) {
        //for all start and end of building put them into List of BuildingPoint
        BuildingPoint[] buildingPoints = new BuildingPoint[buildings.length*2];
        int index = 0;
        for(int building[] : buildings) {
            buildingPoints[index] = new BuildingPoint();
            buildingPoints[index].x = building[0];
            buildingPoints[index].isStart = true;
            buildingPoints[index].height = building[2];

            buildingPoints[index + 1] = new BuildingPoint();
            buildingPoints[index + 1].x = building[1];
            buildingPoints[index + 1].isStart = false;
            buildingPoints[index + 1].height = building[2];
            index += 2;
        }
        Arrays.sort(buildingPoints);

        //using TreeMap because it gives log time performance.
        //PriorityQueue in java does not support remove(object) operation in log time.
        TreeMap<Integer, Integer> queue = new TreeMap<>();
        //PriorityQueue<Integer> queue1 = new PriorityQueue<>(Collections.reverseOrder());
        queue.put(0, 1);
        //queue1.add(0);
        int prevMaxHeight = 0;
        List<int[]> result = new ArrayList<>();
        for(BuildingPoint buildingPoint : buildingPoints) {
            //if it is start of building then add the height to map. If height already exists then increment
            //the value
            if (buildingPoint.isStart) {
                queue.compute(buildingPoint.height, (key, value) -> {
                    if (value != null) {
                        return value + 1;
                    }
                    return 1;
                });
                //  queue1.add(cp.height);
            } else { //if it is end of building then decrement or remove the height from map.
                queue.compute(buildingPoint.height, (key, value) -> {
                    if (value == 1) {
                        return null;
                    }
                    return value - 1;
                });
                // queue1.remove(cp.height);
            }
            //peek the current height after addition or removal of building x.
            int currentMaxHeight = queue.lastKey();
            //int currentMaxHeight = queue1.peek();
            //if height changes from previous height then this building x becomes critcal x.
            // So add it to the result.
            if (prevMaxHeight != currentMaxHeight) {
                result.add(new int[]{buildingPoint.x, currentMaxHeight});
                prevMaxHeight = currentMaxHeight;
            }
        }
        return result;
    }
}

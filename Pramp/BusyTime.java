import java.util.Arrays;

/**
 * Created by Kevin on 7/17/16.
 * Pramp Problem
 */
public class BusyTime {
    public static void main (String[] args) {
        Data[] datas1 = {
                new Data(3, 3, "ENTRY"),
                new Data(4, 6, "EXIT"),
                new Data(1, 1, "ENTRY"),
                new Data(5, 100, "ENTRY"),
                new Data(2, 2, "ENTRY")
        };

        int[] result1 = findBusiestTime(datas1);
        System.out.println(Arrays.toString(result1));

        Data[] datas2 = {
                new Data(3, 1, "EXIT"),
                new Data(4, 8, "EXIT"),
                new Data(1, 4, "ENTRY"),
                new Data(2, 10, "ENTRY")
        };

        int[] result2 = findBusiestTime(datas2);
        System.out.println(Arrays.toString(result2));
    }

    private static int[] findBusiestTime(Data[] arr) {
        int[] result = { -1, -1 };
        if (arr == null) return result;

        // Inline comparator implementation
//        Arrays.sort(arr, new Comparator<Data>() {
//            @Override
//            public int compare(Data d1, Data d2) {
//                return d1.time - d2.time;
//            }
//        });

        // Comparator implemented in class
        Arrays.sort(arr);

        int max = Integer.MIN_VALUE;
        int currMax = 0;
        for (int i = 0; i < arr.length; i++) {
            // Remember that == here compares object refs
//            if (arr[i].type == "ENTRY") {
            if (arr[i].type.equals("ENTRY")) {
                currMax += arr[i].count;
//            } else if (arr[i].type == "EXIT") {
            } else if (arr[i].type.equals("EXIT")) {
                currMax -= arr[i].count;
            }

            if (i + 1 < arr.length && arr[i].time == arr[i + 1].time) {
                continue;
            }

            if (max < currMax) {
                max = currMax;
                result[0] = arr[i].time;
                if (i < arr.length - 1) {
                    result[1] = arr[i + 1].time;
                } else {
                    result[1] = arr[i].time;
                }
            }
        }
        System.out.println("max: " + max);
        return result;
    }
}

class Data implements Comparable<Data> {
    int time;
    int count;
    String type;

    Data (int t, int c, String ty) {
        this.time = t;
        this.count = c;
        this.type = ty;
    }

    public int compareTo(Data d2) {
        return this.time - d2.time;
    }
}

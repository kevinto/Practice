/**
 * Created by kevinto on 12/16/16.
 */
public class WordWrap {
    public static void main(String[] args) {
        String test1 = "Geeks for Geeks presents word wrap problem";
//        String test1 = "Geeks for Geeks";
        String[] strArr1 = test1.split(" ");
        System.out.println("min cost recursive: " + minCostRecursive(strArr1,15, 0));
        System.out.println("min cost dp: " + minCostDp(strArr1,15));
    }

    private static int minCostDp(String[] arr, int width) {
        int[] dp = new int[arr.length + 1];
        for (int dpIdx = dp.length - 2; dpIdx >= 0; dpIdx--) {

            dp[dpIdx] = Integer.MAX_VALUE;
            for (int i = dpIdx; i < arr.length; i++) {
                int currWidth = getWidth(arr, dpIdx, i);
                if (currWidth > width) {
                    break;
                }

                int lineCost = (width - currWidth) * (width - currWidth);
                dp[dpIdx] = Math.min(dp[dpIdx], lineCost + dp[i + 1]);
            }
        }

        return dp[0];
    }

    private static int minCostRecursive(String[] arr, int width, int start) {
        if (start >= arr.length) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int i = start; i < arr.length; i++) {
            int currWidth = getWidth(arr, start, i);
            if (currWidth > width) {
                // Reached a point where adding more
                // words to the line will break our width limit
                break;
            }

            int lineCost = (width - currWidth) * (width - currWidth);
            min = Math.min(min, lineCost + minCostRecursive(arr, width, i + 1));
        }
        return min;
    }

    private static int getWidth(String[] arr, int start, int end) {
        int width = 0;
        for (int i = start; i <= end; i++) {
            width += arr[i].length();
            if (i != end) {
                // For the space between words
                width++;
            }
        }
        return width;
    }
}

/**
 * Created by Kevin on 4/25/16.
 */
public class CircleSorted {
    public static void main(String[] args) {
        int[] arr = {2,3,4,5,0,1};
        System.out.println(isCircleSorted(arr));

        int[] arr1 = {4,1,2,5};
        System.out.println(isCircleSorted(arr1));
    }

    public static boolean isCircleSorted(int[] a) {
        int disorder = 0;
        for (int i = 0; i < a.length; i++) {
            if ( a[(i == 0 ? a.length : i) - 1] > a[i] ) {
                disorder++;
            }

            if (disorder > 1) {
                return false;
            }
        }
        return disorder <= 1;
    }
}

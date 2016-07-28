import java.util.ArrayList;

/**
 * Created by Kevin on 7/23/16.
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(5000);
        arr.add(5000);
        arr.add(5000);

        System.out.println(removeDuplicates(arr));
        System.out.println(arr);
    }

    public static int removeDuplicates(ArrayList<Integer> a) {
        int size = a.size();
        if (size < 2) return size;

        int i = 1;
        int k = 0;
        while (i < size) {
            if (a.get(k).equals(a.get(i))) {
                i++;
            } else {
                k++;
                a.set(k, a.get(i));
                i++;
            }
        }

        return k + 1;
    }
}
